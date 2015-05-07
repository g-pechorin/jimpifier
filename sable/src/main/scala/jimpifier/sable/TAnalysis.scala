package jimpifier.sable

import soot.jimple.parser.analysis.Analysis
import soot.jimple.parser.node._

trait TAnalysis extends Analysis {
	override def getIn(node: Node): AnyRef = ???

	override def caseAAnnotationModifier(node: AAnnotationModifier): Unit = ???

	override def caseAIdentClassName(node: AIdentClassName): Unit = ???

	override def caseAStrictfpModifier(node: AStrictfpModifier): Unit = ???

	override def caseAQuotedClassName(node: AQuotedClassName): Unit = ???

	override def caseTTableswitch(node: TTableswitch): Unit = ???

	override def caseAMultBinop(node: AMultBinop): Unit = ???

	override def caseTRBrace(node: TRBrace): Unit = ???

	override def caseAFieldMember(node: AFieldMember): Unit = ???

	override def caseALabelName(node: ALabelName): Unit = ???

	override def caseASpecialNonstaticInvoke(node: ASpecialNonstaticInvoke): Unit = ???

	override def caseTClass(node: TClass): Unit = ???

	override def caseAConstantCaseLabel(node: AConstantCaseLabel): Unit = ???

	override def caseTTo(node: TTo): Unit = ???

	override def caseTByte(node: TByte): Unit = ???

	override def caseACmpgBinop(node: ACmpgBinop): Unit = ???

	override def caseTExtends(node: TExtends): Unit = ???

	override def caseTIf(node: TIf): Unit = ???

	override def caseTXor(node: TXor): Unit = ???

	override def caseAClzzConstant(node: AClzzConstant): Unit = ???

	override def caseTLBracket(node: TLBracket): Unit = ???

	override def caseTRet(node: TRet): Unit = ???

	override def caseTCmpne(node: TCmpne): Unit = ???

	override def caseTPublic(node: TPublic): Unit = ???

	override def caseACmpeqBinop(node: ACmpeqBinop): Unit = ???

	override def caseANopStatement(node: ANopStatement): Unit = ???

	override def caseAProtectedModifier(node: AProtectedModifier): Unit = ???

	override def caseAMultiArgList(node: AMultiArgList): Unit = ???

	override def caseAClassFileType(node: AClassFileType): Unit = ???

	override def caseAVoidType(node: AVoidType): Unit = ???

	override def caseAMinusBinop(node: AMinusBinop): Unit = ???

	override def caseAImplementsClause(node: AImplementsClause): Unit = ???

	override def caseAAndBinop(node: AAndBinop): Unit = ???

	override def caseTReturn(node: TReturn): Unit = ???

	override def caseACmpgtBinop(node: ACmpgtBinop): Unit = ???

	override def caseTExitmonitor(node: TExitmonitor): Unit = ???

	override def caseAQuotedName(node: AQuotedName): Unit = ???

	override def caseASingleNameList(node: ASingleNameList): Unit = ???

	override def caseAModBinop(node: AModBinop): Unit = ???

	override def caseTNop(node: TNop): Unit = ???

	override def caseTEnum(node: TEnum): Unit = ???

	override def caseTMinus(node: TMinus): Unit = ???

	override def caseTPlus(node: TPlus): Unit = ???

	override def caseTDouble(node: TDouble): Unit = ???

	override def caseACaseStmt(node: ACaseStmt): Unit = ???

	override def caseANonvoidJimpleType(node: ANonvoidJimpleType): Unit = ???

	override def caseTInstanceof(node: TInstanceof): Unit = ???

	override def caseAOrBinop(node: AOrBinop): Unit = ???

	override def caseTBoolConstant(node: TBoolConstant): Unit = ???

	override def caseADoubleBaseType(node: ADoubleBaseType): Unit = ???

	override def caseStart(node: Start): Unit = ???

	override def caseAArrayReference(node: AArrayReference): Unit = ???

	override def caseAFullIdentClassName(node: AFullIdentClassName): Unit = ???

	override def caseACharBaseType(node: ACharBaseType): Unit = ???

	override def caseABreakpointStatement(node: ABreakpointStatement): Unit = ???

	override def setIn(node: Node, o: scala.Any): Unit = ???

	override def caseACmpBinop(node: ACmpBinop): Unit = ???

	override def caseAShlBinop(node: AShlBinop): Unit = ???

	override def caseTDot(node: TDot): Unit = ???

	override def caseACmpneBinop(node: ACmpneBinop): Unit = ???

	override def caseAExitmonitorStatement(node: AExitmonitorStatement): Unit = ???

	override def caseAFloatConstant(node: AFloatConstant): Unit = ???

	override def caseAUnopExpression(node: AUnopExpression): Unit = ???

	override def caseAUnopBoolExpr(node: AUnopBoolExpr): Unit = ???

	override def caseTBoolean(node: TBoolean): Unit = ???

	override def caseAIdentArrayRef(node: AIdentArrayRef): Unit = ???

	override def caseACatchClause(node: ACatchClause): Unit = ???

	override def caseAFloatBaseType(node: AFloatBaseType): Unit = ???

	override def caseTStatic(node: TStatic): Unit = ???

	override def caseADivBinop(node: ADivBinop): Unit = ???

	override def caseAStaticModifier(node: AStaticModifier): Unit = ???

	override def caseAMultiNewExpr(node: AMultiNewExpr): Unit = ???

	override def caseTIgnored(node: TIgnored): Unit = ???

	override def caseAByteBaseType(node: AByteBaseType): Unit = ???

	override def caseACmpgeBinop(node: ACmpgeBinop): Unit = ???

	override def caseAAbstractModifier(node: AAbstractModifier): Unit = ???

	override def caseAIdentName(node: AIdentName): Unit = ???

	override def caseANullBaseTypeNoName(node: ANullBaseTypeNoName): Unit = ???

	override def getOut(node: Node): AnyRef = ???

	override def caseTFloatConstant(node: TFloatConstant): Unit = ???

	override def caseAEntermonitorStatement(node: AEntermonitorStatement): Unit = ???

	override def caseTAtIdentifier(node: TAtIdentifier): Unit = ???

	override def caseAClassNameBaseType(node: AClassNameBaseType): Unit = ???

	override def caseTShr(node: TShr): Unit = ???

	override def caseAExtendsClause(node: AExtendsClause): Unit = ???

	override def caseAShrBinop(node: AShrBinop): Unit = ???

	override def caseAArrayBrackets(node: AArrayBrackets): Unit = ???

	override def caseAPlusBinop(node: APlusBinop): Unit = ???

	override def caseTCmpeq(node: TCmpeq): Unit = ???

	override def caseAFile(node: AFile): Unit = ???

	override def caseTCmpg(node: TCmpg): Unit = ???

	override def caseAClassNameSingleClassNameList(node: AClassNameSingleClassNameList): Unit = ???

	override def caseTShl(node: TShl): Unit = ???

	override def caseABooleanBaseType(node: ABooleanBaseType): Unit = ???

	override def caseAUnopExpr(node: AUnopExpr): Unit = ???

	override def caseAThrowStatement(node: AThrowStatement): Unit = ???

	override def caseTSemicolon(node: TSemicolon): Unit = ???

	override def caseTEntermonitor(node: TEntermonitor): Unit = ???

	override def caseANewExpression(node: ANewExpression): Unit = ???

	override def caseTStaticinvoke(node: TStaticinvoke): Unit = ???

	override def caseABinopExpr(node: ABinopExpr): Unit = ???

	override def caseASingleArgList(node: ASingleArgList): Unit = ???

	override def caseTPrivate(node: TPrivate): Unit = ???

	override def caseAUnknownJimpleType(node: AUnknownJimpleType): Unit = ???

	override def caseTFrom(node: TFrom): Unit = ???

	override def caseTUshr(node: TUshr): Unit = ???

	override def caseAShortBaseType(node: AShortBaseType): Unit = ???

	override def caseTNew(node: TNew): Unit = ???

	override def caseAConstantImmediate(node: AConstantImmediate): Unit = ???

	override def caseTStrictfp(node: TStrictfp): Unit = ???

	override def caseASigFieldRef(node: ASigFieldRef): Unit = ???

	override def caseADoubleBaseTypeNoName(node: ADoubleBaseTypeNoName): Unit = ???

	override def caseTStringConstant(node: TStringConstant): Unit = ???

	override def caseTFloat(node: TFloat): Unit = ???

	override def caseABaseNonvoidType(node: ABaseNonvoidType): Unit = ???

	override def caseTDynamicinvoke(node: TDynamicinvoke): Unit = ???

	override def caseAShortBaseTypeNoName(node: AShortBaseTypeNoName): Unit = ???

	override def caseAInvokeExpression(node: AInvokeExpression): Unit = ???

	override def caseALengthofUnop(node: ALengthofUnop): Unit = ???

	override def caseAFullMethodBody(node: AFullMethodBody): Unit = ???

	override def caseABinopExpression(node: ABinopExpression): Unit = ???

	override def caseTMod(node: TMod): Unit = ???

	override def caseASynchronizedModifier(node: ASynchronizedModifier): Unit = ???

	override def caseADeclaration(node: ADeclaration): Unit = ???

	override def caseAUshrBinop(node: AUshrBinop): Unit = ???

	override def caseTInterfaceinvoke(node: TInterfaceinvoke): Unit = ???

	override def caseAInvokeStatement(node: AInvokeStatement): Unit = ???

	override def caseTBreakpoint(node: TBreakpoint): Unit = ???

	override def caseAMultiParameterList(node: AMultiParameterList): Unit = ???

	override def caseAIntBaseType(node: AIntBaseType): Unit = ???

	override def caseTVolatile(node: TVolatile): Unit = ???

	override def caseAIdentityNoTypeStatement(node: AIdentityNoTypeStatement): Unit = ???

	override def caseTVirtualinvoke(node: TVirtualinvoke): Unit = ???

	override def caseTRBracket(node: TRBracket): Unit = ???

	override def caseTLookupswitch(node: TLookupswitch): Unit = ???

	override def caseAMultiLocalNameList(node: AMultiLocalNameList): Unit = ???

	override def caseTCmp(node: TCmp): Unit = ???

	override def caseTTransient(node: TTransient): Unit = ???

	override def setOut(node: Node, o: scala.Any): Unit = ???

	override def caseTAnnotation(node: TAnnotation): Unit = ???

	override def caseEOF(node: EOF): Unit = ???

	override def caseTDefault(node: TDefault): Unit = ???

	override def caseAFinalModifier(node: AFinalModifier): Unit = ???

	override def caseAIntegerConstant(node: AIntegerConstant): Unit = ???

	override def caseAArrayDescriptor(node: AArrayDescriptor): Unit = ???

	override def caseATableswitchStatement(node: ATableswitchStatement): Unit = ???

	override def caseTGoto(node: TGoto): Unit = ???

	override def caseABinopBoolExpr(node: ABinopBoolExpr): Unit = ???

	override def caseAVolatileModifier(node: AVolatileModifier): Unit = ???

	override def caseAMethodMember(node: AMethodMember): Unit = ???

	override def caseAReferenceVariable(node: AReferenceVariable): Unit = ???

	override def caseASingleLocalNameList(node: ASingleLocalNameList): Unit = ???

	override def caseTVoid(node: TVoid): Unit = ???

	override def caseAMultiNameList(node: AMultiNameList): Unit = ???

	override def caseAArrayNewExpr(node: AArrayNewExpr): Unit = ???

	override def caseAReferenceExpression(node: AReferenceExpression): Unit = ???

	override def caseASingleParameterList(node: ASingleParameterList): Unit = ???

	override def caseAEnumModifier(node: AEnumModifier): Unit = ???

	override def caseTSynchronized(node: TSynchronized): Unit = ???

	override def caseAAssignStatement(node: AAssignStatement): Unit = ???

	override def caseAGotoStatement(node: AGotoStatement): Unit = ???

	override def caseADynamicInvokeExpr(node: ADynamicInvokeExpr): Unit = ???

	override def caseACmpltBinop(node: ACmpltBinop): Unit = ???

	override def caseTCmpgt(node: TCmpgt): Unit = ???

	override def caseTQuotedName(node: TQuotedName): Unit = ???

	override def caseABooleanBaseTypeNoName(node: ABooleanBaseTypeNoName): Unit = ???

	override def caseTCmplt(node: TCmplt): Unit = ???

	override def caseTCmpge(node: TCmpge): Unit = ???

	override def caseTComma(node: TComma): Unit = ???

	override def caseAQuotedNonvoidType(node: AQuotedNonvoidType): Unit = ???

	override def caseTMult(node: TMult): Unit = ???

	override def caseTCmpl(node: TCmpl): Unit = ???

	override def caseANullConstant(node: ANullConstant): Unit = ???

	override def caseTEquals(node: TEquals): Unit = ???

	override def caseTThrows(node: TThrows): Unit = ???

	override def caseAThrowsClause(node: AThrowsClause): Unit = ???

	override def caseTWith(node: TWith): Unit = ???

	override def caseANullBaseType(node: ANullBaseType): Unit = ???

	override def caseAByteBaseTypeNoName(node: AByteBaseTypeNoName): Unit = ???

	override def caseAFieldSignature(node: AFieldSignature): Unit = ???

	override def caseAFileBody(node: AFileBody): Unit = ???

	override def caseTNewarray(node: TNewarray): Unit = ???

	override def caseTNewmultiarray(node: TNewmultiarray): Unit = ???

	override def caseAClassNameMultiClassNameList(node: AClassNameMultiClassNameList): Unit = ???

	override def caseAImmediateExpression(node: AImmediateExpression): Unit = ???

	override def caseANativeModifier(node: ANativeModifier): Unit = ???

	override def caseTProtected(node: TProtected): Unit = ???

	override def caseTDiv(node: TDiv): Unit = ???

	override def caseTInterface(node: TInterface): Unit = ???

	override def caseTCmple(node: TCmple): Unit = ???

	override def caseTLengthof(node: TLengthof): Unit = ???

	override def caseALocalName(node: ALocalName): Unit = ???

	override def caseAInstanceofExpression(node: AInstanceofExpression): Unit = ???

	override def caseALocalFieldRef(node: ALocalFieldRef): Unit = ???

	override def caseAFullIdentNonvoidType(node: AFullIdentNonvoidType): Unit = ???

	override def caseTShort(node: TShort): Unit = ???

	override def caseALongBaseType(node: ALongBaseType): Unit = ???

	override def caseACmpleBinop(node: ACmpleBinop): Unit = ???

	override def caseAFieldReference(node: AFieldReference): Unit = ???

	override def caseAQuotedArrayRef(node: AQuotedArrayRef): Unit = ???

	override def caseALocalVariable(node: ALocalVariable): Unit = ???

	override def caseTFullIdentifier(node: TFullIdentifier): Unit = ???

	override def caseTLParen(node: TLParen): Unit = ???

	override def caseTNative(node: TNative): Unit = ???

	override def caseAPublicModifier(node: APublicModifier): Unit = ???

	override def caseAFloatBaseTypeNoName(node: AFloatBaseTypeNoName): Unit = ???

	override def caseAEmptyMethodBody(node: AEmptyMethodBody): Unit = ???

	override def caseAParameter(node: AParameter): Unit = ???

	override def caseTNull(node: TNull): Unit = ???

	override def caseTAbstract(node: TAbstract): Unit = ???

	override def caseTSpecialinvoke(node: TSpecialinvoke): Unit = ???

	override def caseACharBaseTypeNoName(node: ACharBaseTypeNoName): Unit = ???

	override def caseTNeg(node: TNeg): Unit = ???

	override def caseAGotoStmt(node: AGotoStmt): Unit = ???

	override def caseAVirtualNonstaticInvoke(node: AVirtualNonstaticInvoke): Unit = ???

	override def caseAIfStatement(node: AIfStatement): Unit = ???

	override def caseAIdentityStatement(node: AIdentityStatement): Unit = ???

	override def caseACastExpression(node: ACastExpression): Unit = ???

	override def caseAUnnamedMethodSignature(node: AUnnamedMethodSignature): Unit = ???

	override def caseTIdentifier(node: TIdentifier): Unit = ???

	override def caseAStringConstant(node: AStringConstant): Unit = ???

	override def caseATransientModifier(node: ATransientModifier): Unit = ???

	override def caseTImplements(node: TImplements): Unit = ???

	override def caseTQuote(node: TQuote): Unit = ???

	override def caseTInt(node: TInt): Unit = ???

	override def caseALongBaseTypeNoName(node: ALongBaseTypeNoName): Unit = ???

	override def caseTCatch(node: TCatch): Unit = ???

	override def caseALocalImmediate(node: ALocalImmediate): Unit = ???

	override def caseAIdentNonvoidType(node: AIdentNonvoidType): Unit = ???

	override def caseTOr(node: TOr): Unit = ???

	override def caseTChar(node: TChar): Unit = ???

	override def caseTLong(node: TLong): Unit = ???

	override def caseAStaticInvokeExpr(node: AStaticInvokeExpr): Unit = ???

	override def caseTThrow(node: TThrow): Unit = ???

	override def caseADefaultCaseLabel(node: ADefaultCaseLabel): Unit = ???

	override def caseTColon(node: TColon): Unit = ???

	override def caseANovoidType(node: ANovoidType): Unit = ???

	override def caseTLBrace(node: TLBrace): Unit = ???

	override def caseTCase(node: TCase): Unit = ???

	override def caseAXorBinop(node: AXorBinop): Unit = ???

	override def caseTNullType(node: TNullType): Unit = ???

	override def caseANonstaticInvokeExpr(node: ANonstaticInvokeExpr): Unit = ???

	override def caseTRParen(node: TRParen): Unit = ???

	override def caseAInterfaceNonstaticInvoke(node: AInterfaceNonstaticInvoke): Unit = ???

	override def caseTAnd(node: TAnd): Unit = ???

	override def caseTCls(node: TCls): Unit = ???

	override def caseTUnknown(node: TUnknown): Unit = ???

	override def caseTColonEquals(node: TColonEquals): Unit = ???

	override def caseTFinal(node: TFinal): Unit = ???

	override def caseAMethodSignature(node: AMethodSignature): Unit = ???

	override def caseANegUnop(node: ANegUnop): Unit = ???

	override def caseASimpleNewExpr(node: ASimpleNewExpr): Unit = ???

	override def caseACmplBinop(node: ACmplBinop): Unit = ???

	override def caseARetStatement(node: ARetStatement): Unit = ???

	override def caseAFixedArrayDescriptor(node: AFixedArrayDescriptor): Unit = ???

	override def caseAReturnStatement(node: AReturnStatement): Unit = ???

	override def caseALookupswitchStatement(node: ALookupswitchStatement): Unit = ???

	override def caseAIntBaseTypeNoName(node: AIntBaseTypeNoName): Unit = ???

	override def caseAInterfaceFileType(node: AInterfaceFileType): Unit = ???

	override def caseAPrivateModifier(node: APrivateModifier): Unit = ???

	override def caseTIntegerConstant(node: TIntegerConstant): Unit = ???

	override def caseALabelStatement(node: ALabelStatement): Unit = ???
}
