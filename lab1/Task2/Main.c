#include <stdio.h>

#include "Unary_Function.h"
#include "Square.h"
#include "Linear.h"


int main(void){
	UnaryFunction* f1= (UnaryFunction*)new_Square(-2,2);
	tabulate(f1);
	UnaryFunction* f2= (UnaryFunction*)new_Linear(-2,2,5,-2);
	tabulate(f2);
	printf("f1==f2: %s\n", same_functions_for_ints(f1, f2, 1E-6)?"DA":"NE");
	printf("neg_val f2(1) = %lf\n", ((doubleFuncPtr)f2->table[1])(f2,1.0));
	free(f1);
	free(f2);
	return 0;
}