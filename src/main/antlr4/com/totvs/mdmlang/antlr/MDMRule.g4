grammar MDMRule;
 
// Parser Rules

mdmModel : fieldRules+;
 
fieldRules : CAMPOMASTER ':' transformationSequence+;


//each rule sentence must follow this grammar
transformationSequence : operation OF? (field SEPARATOR?)+;
 
field : TEXT;
  
operation 
	: onlyNumbersOperation 
	| trimOperation 
	| removeLeadingZerosOperation 
	| removeNCharsFromSideOperation 
	| returnOperation
	| substringOperation
	| truncateOperation
	;
	
// Lexer Rules

//Operations
onlyNumbersOperation : 'keep'? 'only numbers';
trimOperation : 'trim';
removeLeadingZerosOperation : 'remove leading zeros';
removeNCharsFromSideOperation : 'remove' QUANTITY (('chars' | 'char') OF? SIDE | SIDE ('chars' | 'char'));
returnOperation : 'return';
substringOperation : 'substring' QUANTITY 'to' QUANTITY; 
truncateOperation : 'truncate' 'at'? QUANTITY; 

// Some constants 
OF : 'of' | 'from' | 'to'; 
SIDE : 'left' | 'right';

SEPARATOR : (';' | ',' | 'and' | 'then');

// Lexer 
CAMPOMASTER : ('a'..'z' | 'A' .. 'Z')+ ;
TEXT : ('a'..'z' | '-')+ ;
QUANTITY : ('0' .. '9')+ ;

WHITESPACE : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ -> skip ; 