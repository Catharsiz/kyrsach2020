import org.antlr.v4.runtime.tree.*;
import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
import java.util.*;


public class MyVisitor extends gBaseVisitor<Object> {


    Stack <HashMap <String, Value>> Stack_var = new Stack<>();;
    HashMap<String, Value> tableforprint = new HashMap<>();
    HashMap<String, gParser.BlockContext> function = new HashMap<>();
    private HashSet<String> globalNames = new HashSet<>();
    boolean isWhile = false;

    private Value getVariable(String varName) throws Exception {
        if (tableforprint.containsKey(varName))
            return tableforprint.get(varName);
        for (HashMap<String, Value> hm : Stack_var) {
            if (hm.containsKey(varName)) {
                return hm.get(varName);
            }
        }
        throw new Exception("No such variable in the table");
    }


    private void setVariable(String variableName, Value value) throws Exception {
        value.setIdent(variableName);
        if (tableforprint.containsKey(variableName)) {

                tableforprint.replace(variableName, value);

                Value val = getVariable(variableName);

                if(val.getType().equals("INTEGER")){
                        String str = "%" + (LLVMGenerator.reg-1) ;
                        LLVMGenerator.assign_i32(variableName, str,globalNames);

                } else
                {
                    String str = "%" + (LLVMGenerator.reg-1) ;
                    LLVMGenerator.assign_double(variableName, str,globalNames);
                }

        }
        else
            for (HashMap<String, Value> cv: Stack_var) {
                if (cv.containsKey(variableName)) {
                    cv.replace(variableName, value);
                }
                else throw  new Exception("Variable" + variableName + " is not identified");
            }
    }

    private void callProcedure(String ident) throws Exception{
        if (function.containsKey(ident)) {
          //  visit(function.get(ident));
            LLVMGenerator.call(ident);

        }
        else throw new Exception("Procedure" + ident + " is not identified");
    }


    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitGo(gParser.GoContext ctx) {
        visitChildren(ctx);
        LLVMGenerator.generate();
        return null;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitBlock(gParser.BlockContext ctx) {
        boolean stack = false;
        if (!tableforprint.isEmpty()) {
            Stack_var.push(tableforprint);
            stack = true;
        }
        visitChildren(ctx);
        if (stack){
            tableforprint = Stack_var.pop();
        } else
            tableforprint.clear();


        return null;
    }


    @Override public Object visitVars(gParser.VarsContext ctx) {

        String type;
        Value value;

        String name = ctx.ident().getText();

        if (ctx.children.contains(ctx.number())){
            Object num = ctx.number().getText();
            type = "INTEGER";
            value = new Value(name, type, num);
            tableforprint.put(name,value);

            System.out.println( "Переменная: " + value.getIdent() + " ,  тип переменной: "+ value.getType() + " , Значение: " + value.getValue());
            globalNames.add(value.getIdent());
            LLVMGenerator.declare_i32(value.getIdent(),true);
            LLVMGenerator.assign_i32(value.getIdent(), value.getValue().toString(), globalNames);
        }
        else if (ctx.children.contains(ctx.floatnumber())){
            Object floatnum = ctx.floatnumber().getText();
            type = "FLOAT";
            value = new Value(name, type, floatnum);
            tableforprint.put(name,value);

            System.out.println( "Переменная: " + value.getIdent() + " ,  тип переменной: "+ value.getType() + " , Значение: " + value.getValue());
            globalNames.add(value.getIdent());
            LLVMGenerator.declare_double(value.getIdent(),true);
            LLVMGenerator.assign_double(value.getIdent(), value.getValue().toString(), globalNames);

        }
        return visitChildren(ctx);
    }



    @Override public Object visitProcedure(gParser.ProcedureContext ctx) {
        String ident = ctx.ident().getText();
        function.put(ident, ctx.block());
        LLVMGenerator.function_start(ident);
        visit(ctx.block());
        LLVMGenerator.function_end();
        return null;
    }

    @Override public Object visitStatement(gParser.StatementContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitAssignstmt(gParser.AssignstmtContext ctx) {
        String variableName = ctx.ident().getText();
        Value value = (Value) visit(ctx.expression());
        try {
            setVariable(variableName, value);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override public Object visitCallstmt(gParser.CallstmtContext ctx) {
        try {
            callProcedure(ctx.ident().getText());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override public Object visitPrintmess(gParser.PrintmessContext ctx) {

        String name;

        if(ctx.children.contains(ctx.expression())){
            name = ctx.expression().getText();
            System.out.println("output переменной " + tableforprint.get(name));
            try {
                Value val = getVariable(name);

                if(val.getType().equals("INTEGER"))
                LLVMGenerator.printf_i32(name,globalNames);
                else LLVMGenerator.printf_double(name,globalNames);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ctx.children.contains(ctx.literal()))
        {
            name = Utils.removeChatAt(ctx.literal().getText());
            LLVMGenerator.printlit(name);
        }
        return visitChildren(ctx);
    }



    @Override public Object visitBeginstmt(gParser.BeginstmtContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitIfstmt(gParser.IfstmtContext ctx) {
        Value value = (Value) visit(ctx.condlast());



        LLVMGenerator.if_start();
      //  if (Boolean.parseBoolean(value.getValue().toString())){
            visit(ctx.beginstmt());
       // }
        LLVMGenerator.if_end();
        return null;
    }

    @Override public Object visitWhilestmt(gParser.WhilestmtContext ctx) {
        LLVMGenerator.while_start();
        Value value = (Value) visit(ctx.condlast());
        LLVMGenerator.while_condition(LLVMGenerator.reg-1);

            visit(ctx.beginstmt());
            value = (Value) visit(ctx.condlast());

        LLVMGenerator.while_end();
        return null;
    }



    @Override public Object visitBreakstmt(gParser.BreakstmtContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitContinuestmt(gParser.ContinuestmtContext ctx) { return visitChildren(ctx); }

    @Override public Object visitIdent(gParser.IdentContext ctx) { return visitChildren(ctx); }


    private static boolean isExpr(String s)  {

            if (s.matches(".*[a-zA-Z ].*") ) {
                return true;
            }
            else {
                return false;
            }
    }

    @Override public Object visitExpr_op(gParser.Expr_opContext ctx) {
        Value left = (Value) visit(ctx.expression());
        Value right = (Value) visit(ctx.term());

        switch (ctx.op.getText()) {
            case "+":
                    if(left.getType().equals("INTEGER")){

                    if (Utils.CheckType(left, right)) {
                        Object obj = Utils.Sum(left, right);

                        String firstValue;

                        if (isExpr(ctx.expression().getText())) {
                            LLVMGenerator.load_i32(ctx.expression().getText(), globalNames);
                            firstValue = "%" + (LLVMGenerator.reg - 1);
                        } else {
                            firstValue = left.getValue().toString();
                        }


                        String secondValue;

                        if (isExpr(ctx.term().getText())) {
                            LLVMGenerator.load_i32(ctx.term().getText(), globalNames);
                            secondValue = "%" + (LLVMGenerator.reg - 1);
                        } else {
                            secondValue = right.getValue().toString();
                        }


                        LLVMGenerator.add_i32(firstValue, secondValue);
                        return obj;
                    }
                    }

                    else {          // + float
                        if (Utils.CheckType(left, right)) {
                            Object obj = Utils.Sum(left, right);

                            String firstValue;

                            if (isExpr(ctx.expression().getText())) {
                                LLVMGenerator.load_double(ctx.expression().getText(), globalNames);
                                firstValue = "%" + (LLVMGenerator.reg - 1);
                            } else {
                                firstValue = left.getValue().toString();
                            }


                            String secondValue;

                            if (isExpr(ctx.term().getText())) {
                                LLVMGenerator.load_double(ctx.term().getText(), globalNames);
                                secondValue = "%" + (LLVMGenerator.reg - 1);
                            } else {
                                secondValue = right.getValue().toString();
                            }


                            LLVMGenerator.add_double(firstValue, secondValue);
                            return obj;
                    }
                    }

                break;

            case "-":

                    if (left.getType().equals("INTEGER")) {

                        if (Utils.CheckType(left, right)) {
                            Object obj = Utils.Sub(left, right);

                            String firstValue;

                            if (isExpr(ctx.expression().getText())) {
                                LLVMGenerator.load_i32(ctx.expression().getText(), globalNames);
                                firstValue = "%" + (LLVMGenerator.reg - 1);
                            } else {
                                firstValue = left.getValue().toString();
                            }


                            String secondValue;

                            if (isExpr(ctx.term().getText())) {
                                LLVMGenerator.load_i32(ctx.term().getText(), globalNames);
                                secondValue = "%" + (LLVMGenerator.reg - 1);
                            } else {
                                secondValue = right.getValue().toString();
                            }

                            LLVMGenerator.sub_i32(firstValue, secondValue);

                            return obj;
                        }
                    }  else {          // - float
                            if (Utils.CheckType(left, right)) {
                                Object obj = Utils.Sub(left, right);

                                String firstValue;

                                if (isExpr(ctx.expression().getText())) {
                                    LLVMGenerator.load_double(ctx.expression().getText(), globalNames);
                                    firstValue = "%" + (LLVMGenerator.reg - 1);
                                } else {
                                    firstValue = left.getValue().toString();
                                }


                                String secondValue;

                                if (isExpr(ctx.term().getText())) {
                                    LLVMGenerator.load_double(ctx.term().getText(), globalNames);
                                    secondValue = "%" + (LLVMGenerator.reg - 1);
                                } else {
                                    secondValue = right.getValue().toString();
                                }


                                LLVMGenerator.sub_double(firstValue, secondValue);
                                return obj;
                            }
                        }
                break;
        }
        return null;
    }

    @Override public Object visitTerm_expr(gParser.Term_exprContext ctx) {
        return (Value) visit(ctx.term());
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitTerm_op(gParser.Term_opContext ctx) {
        Value left = (Value) visit(ctx.term());
        Value right = (Value) visit(ctx.factor());
        switch (ctx.op.getText()) {
            case "/":
                if(left.getType().equals("INTEGER")) {
                    if (Utils.CheckType(left, right)) {
                        Object obj = Utils.Div(left, right);


                        String firstValue;

                        if (isExpr(ctx.term().getText())) {
                            LLVMGenerator.load_i32(ctx.term().getText(), globalNames);
                            firstValue = "%" + (LLVMGenerator.reg - 1);
                        } else {
                            firstValue = left.getValue().toString();
                        }


                        String secondValue;

                        if (isExpr(ctx.factor().getText())) {
                            LLVMGenerator.load_i32(ctx.factor().getText(), globalNames);
                            secondValue = "%" + (LLVMGenerator.reg - 1);
                        } else {
                            secondValue = right.getValue().toString();
                        }

                        LLVMGenerator.div_i32(firstValue, secondValue);

                        return obj;
                    }
                }  else {          // деление float
                    if (Utils.CheckType(left, right)) {
                        Object obj = Utils.Div(left, right);

                        String firstValue;

                        if (isExpr(ctx.term().getText())) {
                            LLVMGenerator.load_double(ctx.term().getText(), globalNames);
                            firstValue = "%" + (LLVMGenerator.reg - 1);
                        } else {
                            firstValue = left.getValue().toString();
                        }


                        String secondValue;

                        if (isExpr(ctx.factor().getText())) {
                            LLVMGenerator.load_double(ctx.factor().getText(), globalNames);
                            secondValue = "%" + (LLVMGenerator.reg - 1);
                        } else {
                            secondValue = right.getValue().toString();
                        }


                        LLVMGenerator.div_double(firstValue, secondValue);
                        return obj;
                    }
                }
                break;


            case "*":
                if(left.getType().equals("INTEGER")){
                    if (Utils.CheckType(left, right)) {
                        Object obj = Utils.Mult(left, right);

                        String firstValue;

                        if (isExpr(ctx.term().getText())) {
                            LLVMGenerator.load_i32(ctx.term().getText(), globalNames);
                            firstValue = "%" + (LLVMGenerator.reg - 1);
                        } else {
                            firstValue = left.getValue().toString();
                        }


                        String secondValue;

                        if (isExpr(ctx.factor().getText())) {
                            LLVMGenerator.load_i32(ctx.factor().getText(), globalNames);
                            secondValue = "%" + (LLVMGenerator.reg - 1);
                        } else {
                            secondValue = right.getValue().toString();
                        }

                        LLVMGenerator.mul_i32(firstValue, secondValue);

                        return obj;
                    }
                    }  else {          // умножение float
                        if (Utils.CheckType(left, right)) {
                            Object obj = Utils.Mult(left, right);

                            String firstValue;

                            if (isExpr(ctx.term().getText())) {
                                LLVMGenerator.load_double(ctx.term().getText(), globalNames);
                                firstValue = "%" + (LLVMGenerator.reg - 1);
                            } else {
                                firstValue = left.getValue().toString();
                            }


                            String secondValue;

                            if (isExpr(ctx.factor().getText())) {
                                LLVMGenerator.load_double(ctx.factor().getText(), globalNames);
                                secondValue = "%" + (LLVMGenerator.reg - 1);
                            } else {
                                secondValue = right.getValue().toString();
                            }


                            LLVMGenerator.mul_double(firstValue, secondValue);
                            return obj;
                        }
                    }

                break;
        }
        return null;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitFactor_term(gParser.Factor_termContext ctx) {
        return (Value) visit(ctx.factor());
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitIdent_factor(gParser.Ident_factorContext ctx) {
        try {
            return getVariable(ctx.ident().getText());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitInteger_factor(gParser.Integer_factorContext ctx) {
        return new Value ("", "INTEGER", Integer.parseInt(ctx.number().getText()));
    }



    @Override public Object visitFloat_factor(gParser.Float_factorContext ctx) {
        return new Value ("", "FLOAT", Float.parseFloat(ctx.floatnumber().getText()));
    }




    @Override public Object visitExpr_factor(gParser.Expr_factorContext ctx) {
        return (Value) visit(ctx.expression());
    }



    @Override public Object visitFloatnumber(gParser.FloatnumberContext ctx) { return visitChildren(ctx); }


    @Override public Object visitNumber(gParser.NumberContext ctx) { return visitChildren(ctx); }


    @Override public Object visitCondlast(gParser.CondlastContext ctx) {
        if (ctx.condition().size() > 1) {
            Value left = (Value) visit(ctx.condition(0));

            Value right = (Value) visit(ctx.condition(1));


            if (ctx.check.getText().equals("or")) {
                if(!isExpr(ctx.condition(1).getChild(1).getText()) && !isExpr(ctx.condition(1).getChild(3).getText()) ){
                    LLVMGenerator.or(LLVMGenerator.reg-1,LLVMGenerator.reg-2);
                } else if(isExpr(ctx.condition(1).getChild(1).getText()) && isExpr(ctx.condition(1).getChild(3).getText()) ){
                    LLVMGenerator.or(LLVMGenerator.reg-1,LLVMGenerator.reg-4);
                } else if(!isExpr(ctx.condition(1).getChild(1).getText()) || !isExpr(ctx.condition(1).getChild(3).getText()) ){
                    LLVMGenerator.or(LLVMGenerator.reg-1,LLVMGenerator.reg-3);
                }
                return new Value("", Utils.Bool, (Boolean.parseBoolean(left.getValue().toString()) || Boolean.parseBoolean(right.getValue().toString())));
            } else if (ctx.check.getText().equals("and")) {
                if(!isExpr(ctx.condition(1).getChild(1).getText()) && !isExpr(ctx.condition(1).getChild(3).getText()) ){
                    LLVMGenerator.and(LLVMGenerator.reg-1,LLVMGenerator.reg-2);
                } else if(isExpr(ctx.condition(1).getChild(1).getText()) && isExpr(ctx.condition(1).getChild(3).getText()) ){
                    LLVMGenerator.and(LLVMGenerator.reg-1,LLVMGenerator.reg-4);
                } else if(!isExpr(ctx.condition(1).getChild(1).getText()) || !isExpr(ctx.condition(1).getChild(3).getText()) ){
                    LLVMGenerator.and(LLVMGenerator.reg-1,LLVMGenerator.reg-3);
                }

                return new Value("", Utils.Bool, (Boolean.parseBoolean(left.getValue().toString()) && Boolean.parseBoolean(right.getValue().toString())));
            }
            return visitChildren(ctx);
        }
        else {
            return visitChildren(ctx);
        }
    }

    @Override public Object visitCond_expr_all(gParser.Cond_expr_allContext ctx) {
        Value left = (Value) visit(ctx.expression(0));
        Value right = (Value) visit(ctx.expression(1));
        try {
            if (!Utils.CheckType(left, right)) return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (ctx.check.getText().equals("==")) {
                if (left.getType().equals("INTEGER")) {
                    if (isExpr(ctx.expression(0).getText()) && isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_i32(ctx.expression(0).getText(), globalNames);
                        LLVMGenerator.load_i32(ctx.expression(1).getText(), globalNames);
                        LLVMGenerator.eq2("INTEGER");
                    } else if (isExpr(ctx.expression(0).getText()) && !isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_i32(ctx.expression(0).getText(), globalNames);
                        LLVMGenerator.eq1(ctx.expression(1).getText(), "INTEGER");
                    } else if (!isExpr(ctx.expression(0).getText()) && isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_i32(ctx.expression(1).getText(), globalNames);
                        LLVMGenerator.eq1(ctx.expression(1).getText(), "INTEGER");
                    } else if (!isExpr(ctx.expression(0).getText()) && !isExpr(ctx.expression(1).getText())) {
                        System.out.println(ctx.expression(0).getText());
                        System.out.println(ctx.expression(1).getText());
                        LLVMGenerator.eq0(ctx.expression(0).getText(), ctx.expression(1).getText(), "INTEGER");
                    }
                } else {   //"FLOAT"
                    if (isExpr(ctx.expression(0).getText()) && isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_double(ctx.expression(0).getText(), globalNames);
                        LLVMGenerator.load_double(ctx.expression(1).getText(), globalNames);
                        LLVMGenerator.eq2("FLOAT");

                    } else if (isExpr(ctx.expression(0).getText()) && !isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_double(ctx.expression(0).getText(), globalNames);
                        LLVMGenerator.eq1(ctx.expression(1).getText(), "FLOAT");

                    } else if (!isExpr(ctx.expression(0).getText()) && isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_double(ctx.expression(1).getText(), globalNames);
                        LLVMGenerator.eq1(ctx.expression(0).getText(), "FLOAT");
                    } else if (!isExpr(ctx.expression(0).getText()) && !isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.eq0(ctx.expression(0).getText(), ctx.expression(1).getText(), "FLOAT");
                    }
                }
            } else if (ctx.check.getText().equals(">")) {
                if (left.getType().equals("INTEGER") && right.getType().equals(("INTEGER"))) {

                    if (isExpr(ctx.expression(0).getText()) && isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_i32(ctx.expression(0).getText(), globalNames);
                        LLVMGenerator.load_i32(ctx.expression(1).getText(), globalNames);
                        LLVMGenerator.more2("INTEGER");
                    } else if (isExpr(ctx.expression(0).getText()) && !isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_i32(ctx.expression(0).getText(), globalNames);
                        LLVMGenerator.more1_1(ctx.expression(1).getText(), "INTEGER");
                    } else if (!isExpr(ctx.expression(0).getText()) && isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_i32(ctx.expression(1).getText(), globalNames);
                        LLVMGenerator.more1_2(ctx.expression(0).getText(), "INTEGER");
                    } else if (!isExpr(ctx.expression(0).getText()) && !isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.more0(ctx.expression(0).getText(), ctx.expression(1).getText(), "INTEGER");
                    }
                } else {
                    if (left.getType().equals("FLOAT") && right.getType().equals(("FLOAT")))
                    if (isExpr(ctx.expression(0).getText()) && isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_double(ctx.expression(0).getText(), globalNames);
                        LLVMGenerator.load_double(ctx.expression(1).getText(), globalNames);
                        LLVMGenerator.more2("FLOAT");
                    } else if (isExpr(ctx.expression(0).getText()) && !isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_double(ctx.expression(0).getText(), globalNames);
                        LLVMGenerator.more1_1(ctx.expression(1).getText(), "FLOAT");
                    } else if (!isExpr(ctx.expression(0).getText()) && isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_double(ctx.expression(1).getText(), globalNames);
                        LLVMGenerator.more1_2(ctx.expression(0).getText(), "FLOAT");
                    } else if (!isExpr(ctx.expression(0).getText()) && !isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.more0(ctx.expression(0).getText(), ctx.expression(1).getText(), "FLOAT");
                    }
                }
            } else if (ctx.check.getText().equals("<")) {
                if (left.getType().equals("INTEGER") && right.getType().equals(("INTEGER"))) {
                    if (isExpr(ctx.expression(0).getText()) && isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_i32(ctx.expression(0).getText(), globalNames);
                        LLVMGenerator.load_i32(ctx.expression(1).getText(), globalNames);
                        LLVMGenerator.less2("INTEGER");
                    } else if (isExpr(ctx.expression(0).getText()) && !isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_i32(ctx.expression(0).getText(), globalNames);
                        LLVMGenerator.less1_1(ctx.expression(1).getText(), "INTEGER");
                    } else if (!isExpr(ctx.expression(0).getText()) && isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_i32(ctx.expression(1).getText(), globalNames);
                        LLVMGenerator.less1_2(ctx.expression(0).getText(), "INTEGER");
                    } else if (!isExpr(ctx.expression(0).getText()) && !isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.less0(ctx.expression(0).getText(), ctx.expression(1).getText(), "INTEGER");
                    }
                } else if (left.getType().equals("FLOAT") && right.getType().equals(("FLOAT"))) {
                        if (isExpr(ctx.expression(0).getText()) && isExpr(ctx.expression(1).getText())) {
                            LLVMGenerator.load_double(ctx.expression(0).getText(), globalNames);
                            LLVMGenerator.load_double(ctx.expression(1).getText(), globalNames);
                            LLVMGenerator.less2("FLOAT");
                        } else if (isExpr(ctx.expression(0).getText()) && !isExpr(ctx.expression(1).getText())) {
                            LLVMGenerator.load_double(ctx.expression(0).getText(), globalNames);
                            LLVMGenerator.less1_1(ctx.expression(1).getText(), "FLOAT");
                        } else if (!isExpr(ctx.expression(0).getText()) && isExpr(ctx.expression(1).getText())) {
                            LLVMGenerator.load_double(ctx.expression(1).getText(), globalNames);
                            LLVMGenerator.less1_2(ctx.expression(0).getText(), "FLOAT");
                        } else if (!isExpr(ctx.expression(0).getText()) && !isExpr(ctx.expression(1).getText())) {
                            LLVMGenerator.less0(ctx.expression(0).getText(), ctx.expression(1).getText(), "FLOAT");
                        }
                    }
                } else if (ctx.check.getText().equals(">=")) {
                if (left.getType().equals("INTEGER") && right.getType().equals(("INTEGER"))) {
                    if (isExpr(ctx.expression(0).getText()) && isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_i32(ctx.expression(0).getText(), globalNames);
                        LLVMGenerator.load_i32(ctx.expression(1).getText(), globalNames);
                        LLVMGenerator.moreeq2("INTEGER");
                    } else if (isExpr(ctx.expression(0).getText()) && !isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_i32(ctx.expression(0).getText(), globalNames);
                        LLVMGenerator.moreeq1_1(ctx.expression(1).getText(), "INTEGER");
                    } else if (!isExpr(ctx.expression(0).getText()) && isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_i32(ctx.expression(1).getText(), globalNames);
                        LLVMGenerator.moreeq1_2(ctx.expression(0).getText(), "INTEGER");
                    } else if (!isExpr(ctx.expression(0).getText()) && !isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.moreeq0(ctx.expression(0).getText(), ctx.expression(1).getText(), "INTEGER");
                    }
                } else if (left.getType().equals("FLOAT") && right.getType().equals(("FLOAT"))) {
                    if (isExpr(ctx.expression(0).getText()) && isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_double(ctx.expression(0).getText(), globalNames);
                        LLVMGenerator.load_double(ctx.expression(1).getText(), globalNames);
                        LLVMGenerator.moreeq2("FLOAT");
                    } else if (isExpr(ctx.expression(0).getText()) && !isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_double(ctx.expression(0).getText(), globalNames);
                        LLVMGenerator.moreeq1_1(ctx.expression(1).getText(), "FLOAT");
                    } else if (!isExpr(ctx.expression(0).getText()) && isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_double(ctx.expression(1).getText(), globalNames);
                        LLVMGenerator.moreeq1_2(ctx.expression(0).getText(), "FLOAT");
                    } else if (!isExpr(ctx.expression(0).getText()) && !isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.moreeq0(ctx.expression(0).getText(), ctx.expression(1).getText(), "FLOAT");
                    }
                }
            } else if (ctx.check.getText().equals("<=")) {
                if (left.getType().equals("INTEGER") && right.getType().equals(("INTEGER"))) {
                    if (isExpr(ctx.expression(0).getText()) && isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_i32(ctx.expression(0).getText(), globalNames);
                        LLVMGenerator.load_i32(ctx.expression(1).getText(), globalNames);
                        LLVMGenerator.lesseq2("INTEGER");
                    } else if (isExpr(ctx.expression(0).getText()) && !isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_i32(ctx.expression(0).getText(), globalNames);
                        LLVMGenerator.lesseq1_1(ctx.expression(1).getText(), "INTEGER");
                    } else if (!isExpr(ctx.expression(0).getText()) && isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_i32(ctx.expression(1).getText(), globalNames);
                        LLVMGenerator.lesseq1_2(ctx.expression(0).getText(), "INTEGER");
                    } else if (!isExpr(ctx.expression(0).getText()) && !isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.lesseq0(ctx.expression(0).getText(), ctx.expression(1).getText(), "INTEGER");
                    }
                } else if (left.getType().equals("FLOAT") && right.getType().equals(("FLOAT"))) {
                    if (isExpr(ctx.expression(0).getText()) && isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_double(ctx.expression(0).getText(), globalNames);
                        LLVMGenerator.load_double(ctx.expression(1).getText(), globalNames);
                        LLVMGenerator.lesseq2("FLOAT");
                    } else if (isExpr(ctx.expression(0).getText()) && !isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_double(ctx.expression(0).getText(), globalNames);
                        LLVMGenerator.lesseq1_1(ctx.expression(1).getText(), "FLOAT");
                    } else if (!isExpr(ctx.expression(0).getText()) && isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_double(ctx.expression(1).getText(), globalNames);
                        LLVMGenerator.lesseq1_2(ctx.expression(0).getText(), "FLOAT");
                    } else if (!isExpr(ctx.expression(0).getText()) && !isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.lesseq0(ctx.expression(0).getText(), ctx.expression(1).getText(), "FLOAT");
                    }

                }
            }  else if (ctx.check.getText().equals("!=")) {
                if (left.getType().equals("INTEGER") && right.getType().equals(("INTEGER"))) {
                    if (isExpr(ctx.expression(0).getText()) && isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_i32(ctx.expression(0).getText(), globalNames);
                        LLVMGenerator.load_i32(ctx.expression(1).getText(), globalNames);
                        LLVMGenerator.noeq2("INTEGER");
                    } else if (isExpr(ctx.expression(0).getText()) && !isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_i32(ctx.expression(0).getText(), globalNames);
                        LLVMGenerator.noeq1(ctx.expression(1).getText(), "INTEGER");
                    } else if (!isExpr(ctx.expression(0).getText()) && isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_i32(ctx.expression(1).getText(), globalNames);
                        LLVMGenerator.noeq1(ctx.expression(0).getText(), "INTEGER");
                    } else if (!isExpr(ctx.expression(0).getText()) && !isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.noeq0(ctx.expression(0).getText(), ctx.expression(1).getText(), "INTEGER");
                    }
                } else if (left.getType().equals("FLOAT") && right.getType().equals(("FLOAT"))) {
                    if (isExpr(ctx.expression(0).getText()) && isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_double(ctx.expression(0).getText(), globalNames);
                        LLVMGenerator.load_double(ctx.expression(1).getText(), globalNames);
                        LLVMGenerator.noeq2("FLOAT");
                    } else if (isExpr(ctx.expression(0).getText()) && !isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_double(ctx.expression(0).getText(), globalNames);
                        LLVMGenerator.noeq1(ctx.expression(1).getText(), "FLOAT");
                    } else if (!isExpr(ctx.expression(0).getText()) && isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.load_double(ctx.expression(1).getText(), globalNames);
                        LLVMGenerator.noeq1(ctx.expression(0).getText(), "FLOAT");
                    } else if (!isExpr(ctx.expression(0).getText()) && !isExpr(ctx.expression(1).getText())) {
                        LLVMGenerator.noeq0(ctx.expression(0).getText(), ctx.expression(1).getText(), "FLOAT");
                    }
                }
            }
            Object obj = Utils.Compare(ctx.check.getText(), left, right);
            return obj;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override public Object visitCond_expr(gParser.Cond_exprContext ctx) {
        Value result = (Value) visit(ctx.expression());
        try {
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
