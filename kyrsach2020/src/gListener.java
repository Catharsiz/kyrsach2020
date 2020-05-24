// Generated from C:/Users/User/IdeaProjects/kyrsach2020\g.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link gParser}.
 */
public interface gListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link gParser#go}.
	 * @param ctx the parse tree
	 */
	void enterGo(gParser.GoContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#go}.
	 * @param ctx the parse tree
	 */
	void exitGo(gParser.GoContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(gParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(gParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#consts}.
	 * @param ctx the parse tree
	 */
	void enterConsts(gParser.ConstsContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#consts}.
	 * @param ctx the parse tree
	 */
	void exitConsts(gParser.ConstsContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#vars}.
	 * @param ctx the parse tree
	 */
	void enterVars(gParser.VarsContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#vars}.
	 * @param ctx the parse tree
	 */
	void exitVars(gParser.VarsContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#procedure}.
	 * @param ctx the parse tree
	 */
	void enterProcedure(gParser.ProcedureContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#procedure}.
	 * @param ctx the parse tree
	 */
	void exitProcedure(gParser.ProcedureContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(gParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(gParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#assignstmt}.
	 * @param ctx the parse tree
	 */
	void enterAssignstmt(gParser.AssignstmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#assignstmt}.
	 * @param ctx the parse tree
	 */
	void exitAssignstmt(gParser.AssignstmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#callstmt}.
	 * @param ctx the parse tree
	 */
	void enterCallstmt(gParser.CallstmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#callstmt}.
	 * @param ctx the parse tree
	 */
	void exitCallstmt(gParser.CallstmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#printmess}.
	 * @param ctx the parse tree
	 */
	void enterPrintmess(gParser.PrintmessContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#printmess}.
	 * @param ctx the parse tree
	 */
	void exitPrintmess(gParser.PrintmessContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#beginstmt}.
	 * @param ctx the parse tree
	 */
	void enterBeginstmt(gParser.BeginstmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#beginstmt}.
	 * @param ctx the parse tree
	 */
	void exitBeginstmt(gParser.BeginstmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#ifstmt}.
	 * @param ctx the parse tree
	 */
	void enterIfstmt(gParser.IfstmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#ifstmt}.
	 * @param ctx the parse tree
	 */
	void exitIfstmt(gParser.IfstmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#whilestmt}.
	 * @param ctx the parse tree
	 */
	void enterWhilestmt(gParser.WhilestmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#whilestmt}.
	 * @param ctx the parse tree
	 */
	void exitWhilestmt(gParser.WhilestmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#condlast}.
	 * @param ctx the parse tree
	 */
	void enterCondlast(gParser.CondlastContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#condlast}.
	 * @param ctx the parse tree
	 */
	void exitCondlast(gParser.CondlastContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cond_expr_all}
	 * labeled alternative in {@link gParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCond_expr_all(gParser.Cond_expr_allContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cond_expr_all}
	 * labeled alternative in {@link gParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCond_expr_all(gParser.Cond_expr_allContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cond_expr}
	 * labeled alternative in {@link gParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCond_expr(gParser.Cond_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cond_expr}
	 * labeled alternative in {@link gParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCond_expr(gParser.Cond_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#breakstmt}.
	 * @param ctx the parse tree
	 */
	void enterBreakstmt(gParser.BreakstmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#breakstmt}.
	 * @param ctx the parse tree
	 */
	void exitBreakstmt(gParser.BreakstmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#continuestmt}.
	 * @param ctx the parse tree
	 */
	void enterContinuestmt(gParser.ContinuestmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#continuestmt}.
	 * @param ctx the parse tree
	 */
	void exitContinuestmt(gParser.ContinuestmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_op}
	 * labeled alternative in {@link gParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpr_op(gParser.Expr_opContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_op}
	 * labeled alternative in {@link gParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpr_op(gParser.Expr_opContext ctx);
	/**
	 * Enter a parse tree produced by the {@code term_expr}
	 * labeled alternative in {@link gParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterTerm_expr(gParser.Term_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code term_expr}
	 * labeled alternative in {@link gParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitTerm_expr(gParser.Term_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code term_op}
	 * labeled alternative in {@link gParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm_op(gParser.Term_opContext ctx);
	/**
	 * Exit a parse tree produced by the {@code term_op}
	 * labeled alternative in {@link gParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm_op(gParser.Term_opContext ctx);
	/**
	 * Enter a parse tree produced by the {@code factor_term}
	 * labeled alternative in {@link gParser#term}.
	 * @param ctx the parse tree
	 */
	void enterFactor_term(gParser.Factor_termContext ctx);
	/**
	 * Exit a parse tree produced by the {@code factor_term}
	 * labeled alternative in {@link gParser#term}.
	 * @param ctx the parse tree
	 */
	void exitFactor_term(gParser.Factor_termContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ident_factor}
	 * labeled alternative in {@link gParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterIdent_factor(gParser.Ident_factorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ident_factor}
	 * labeled alternative in {@link gParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitIdent_factor(gParser.Ident_factorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code float_factor}
	 * labeled alternative in {@link gParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFloat_factor(gParser.Float_factorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code float_factor}
	 * labeled alternative in {@link gParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFloat_factor(gParser.Float_factorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code integer_factor}
	 * labeled alternative in {@link gParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterInteger_factor(gParser.Integer_factorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code integer_factor}
	 * labeled alternative in {@link gParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitInteger_factor(gParser.Integer_factorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_factor}
	 * labeled alternative in {@link gParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterExpr_factor(gParser.Expr_factorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_factor}
	 * labeled alternative in {@link gParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitExpr_factor(gParser.Expr_factorContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#ident}.
	 * @param ctx the parse tree
	 */
	void enterIdent(gParser.IdentContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#ident}.
	 * @param ctx the parse tree
	 */
	void exitIdent(gParser.IdentContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#floatnumber}.
	 * @param ctx the parse tree
	 */
	void enterFloatnumber(gParser.FloatnumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#floatnumber}.
	 * @param ctx the parse tree
	 */
	void exitFloatnumber(gParser.FloatnumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(gParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(gParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(gParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(gParser.LiteralContext ctx);
}