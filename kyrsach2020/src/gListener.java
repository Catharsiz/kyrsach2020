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
	 * Enter a parse tree produced by {@link gParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(gParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(gParser.ConditionContext ctx);
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
	 * Enter a parse tree produced by {@link gParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(gParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(gParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(gParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(gParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(gParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(gParser.FactorContext ctx);
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
}