// Generated from C:/Users/User/IdeaProjects/eee\g.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link gParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface gVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link gParser#go}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGo(gParser.GoContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(gParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#consts}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConsts(gParser.ConstsContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#vars}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVars(gParser.VarsContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#procedure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedure(gParser.ProcedureContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(gParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#assignstmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignstmt(gParser.AssignstmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#callstmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallstmt(gParser.CallstmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#writestmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWritestmt(gParser.WritestmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#qstmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQstmt(gParser.QstmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#bangstmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBangstmt(gParser.BangstmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#beginstmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBeginstmt(gParser.BeginstmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#ifstmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfstmt(gParser.IfstmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#whilestmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhilestmt(gParser.WhilestmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(gParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(gParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(gParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(gParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent(gParser.IdentContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(gParser.NumberContext ctx);
}