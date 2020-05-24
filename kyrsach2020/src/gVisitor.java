// Generated from C:/Users/User/IdeaProjects/kyrsach2020\g.g4 by ANTLR 4.8
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
	 * Visit a parse tree produced by {@link gParser#printmess}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintmess(gParser.PrintmessContext ctx);
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
	 * Visit a parse tree produced by {@link gParser#condlast}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondlast(gParser.CondlastContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cond_expr_all}
	 * labeled alternative in {@link gParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond_expr_all(gParser.Cond_expr_allContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cond_expr}
	 * labeled alternative in {@link gParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond_expr(gParser.Cond_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#breakstmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakstmt(gParser.BreakstmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#continuestmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinuestmt(gParser.ContinuestmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_op}
	 * labeled alternative in {@link gParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_op(gParser.Expr_opContext ctx);
	/**
	 * Visit a parse tree produced by the {@code term_expr}
	 * labeled alternative in {@link gParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm_expr(gParser.Term_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code term_op}
	 * labeled alternative in {@link gParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm_op(gParser.Term_opContext ctx);
	/**
	 * Visit a parse tree produced by the {@code factor_term}
	 * labeled alternative in {@link gParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor_term(gParser.Factor_termContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ident_factor}
	 * labeled alternative in {@link gParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent_factor(gParser.Ident_factorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code float_factor}
	 * labeled alternative in {@link gParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloat_factor(gParser.Float_factorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integer_factor}
	 * labeled alternative in {@link gParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger_factor(gParser.Integer_factorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_factor}
	 * labeled alternative in {@link gParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_factor(gParser.Expr_factorContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent(gParser.IdentContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#floatnumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatnumber(gParser.FloatnumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(gParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(gParser.LiteralContext ctx);
}