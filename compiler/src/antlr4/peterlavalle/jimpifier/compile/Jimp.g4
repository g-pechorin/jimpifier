grammar Jimp;


K_class         : 'class';
K_extends       : 'extends';
K_newarray      : 'newarray';
K_static        : 'static';
K_final         : 'final';
K_lengthof      : 'lengthof';
K_clinit        : '<clinit>';
K_init          : '<init>';
K_return        : 'return';
K_specialinvoke : 'specialinvoke';
K_new           : 'new';
K_if            : 'if';
K_goto          : 'goto';
K_default       : 'default';
K_case          : 'case';
K_lookupswitch  : 'lookupswitch';
K_neg           : 'neg';
K_enum          : 'enum';
K_null          : 'null';
K_throw         : 'throw';
K_implements    : 'implements';

K_monitor : 'entermonitor' | 'exitmonitor';
K_visibility : 'public' | 'private' | 'protected';

REGISTER : '$'? ('r'|'l'|'f'|'i'|'b'|'z'|'d'|'c'|'s') [0-9]+;

BRACE_BEGIN : '{';
BRACE_CLOSE : '}';
BRACK_BEGIN : '[';
BRACK_CLOSE : ']';
PAREN_BEGIN : '(';
PAREN_CLOSE : ')';
EQ : '=' |':=';
COMPARE : '!=' | '>=' | '==' | '<=' | '<' | '>';
INFIX : '*' | '+' | '-' | 'cmp' | '/' | 'cmpl' | 'cmpg' | '%' | '>>';

SPECIAL : '@' ('this'|('parameter' ('0'|([1-9][0-9]*)))|'caughtexception');

typename : classname (BRACK_BEGIN BRACK_CLOSE) *;
classname : DUMBNAME ('.'DUMBNAME)* ;

method_name
    : K_clinit
    | K_init
    | DUMBNAME
    ;

module :
    K_visibility? K_final? K_enum? K_class classname K_extends classname (K_implements classname)?
    BRACE_BEGIN
        (field ';')*
        method*
    BRACE_CLOSE;

field : K_visibility? K_static? K_final? K_enum? typename DUMBNAME;

method : K_visibility? K_static? K_final? typename method_name PAREN_BEGIN typename* PAREN_CLOSE
    BRACE_BEGIN
        (registers ';')*
        (statement ';')+
        block*
        (handler ';')*
    BRACE_CLOSE;

block : DUMBNAME ':' (statement ';')+;
handler : 'catch' classname 'from' DUMBNAME 'to' DUMBNAME 'with' DUMBNAME;
registers : typename REGISTER+;

statement
    : K_monitor rvalue #monitor
    | K_throw rvalue #raise
    | lvalue EQ K_newarray PAREN_BEGIN typename PAREN_CLOSE BRACK_BEGIN rvalue BRACK_CLOSE #newarray
	| lvalue EQ rvalue INFIX rvalue #infix
	| lvalue EQ rvalue #assign
	| lvalue EQ K_new typename #allocate
	| lvalue EQ PAREN_BEGIN typename PAREN_CLOSE rvalue #cast
    | (lvalue EQ)?    invoke #statement_invoke
	| (K_if rvalue COMPARE rvalue)? K_goto DUMBNAME #jump
    | K_return rvalue? #resurn
	| K_lookupswitch PAREN_BEGIN rvalue PAREN_CLOSE BRACE_BEGIN (lookup_case ';')+ BRACE_CLOSE #switch
    ;

invoke
	: REGISTER '.' DUMBNAME PAREN_BEGIN rvalue* PAREN_CLOSE                             #invoke_method
    | typename '.' DUMBNAME PAREN_BEGIN rvalue* PAREN_CLOSE                             #invoke_global
    | K_specialinvoke REGISTER '.' (K_init|DUMBNAME) PAREN_BEGIN rvalue* PAREN_CLOSE    #invoke_special
    ;

lookup_case
	: K_default ':' K_goto DUMBNAME #dcase
	| K_case INTEGER ':' K_goto DUMBNAME #ccase
	;

lvalue
    : lvalue (BRACK_BEGIN rvalue BRACK_CLOSE)   #lval_array
    | lvalue '.' DUMBNAME                       #lval_field
    | REGISTER                                  #lval_register
	| DUMBNAME '.' DUMBNAME                     #lval_global
    ;

rvalue
 	: K_null #literal_null
 	| K_class STRING #literal_class
 	| INTEGER #literal_integer
 	| FLOAT #literal_float
 	| LONG #literal_long
 	| DOUBLE #literal_double
 	| lvalue #rvalue_lvalue
 	| STRING #literal_string
 	| K_neg rvalue #negation
    | 'java.lang.Enum.valueOf' PAREN_BEGIN K_class STRING rvalue PAREN_CLOSE #enumValueOf
    | SPECIAL #special
    | K_lengthof rvalue #length
    ;

fragment
Word : ('$'|[A-Za-z_])('$'|[A-Za-z_0-9])*;

DUMBNAME : Word;
FLOAT : '-'? [0-9]+ '.'[0-9]+ ('E-'[1-9][0-9]*)? 'F';
LONG : [0-9]+ 'L';
DOUBLE : '-'? [0-9]+ '.'[0-9]+ ;
STRING : '"' ~('"')* '"';
INTEGER : '-'? [0-9]+;

WS : (' '|'\n'|'\t'|'\r'|',') -> channel(HIDDEN);
