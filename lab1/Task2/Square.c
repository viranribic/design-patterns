#include "Unary_Function.h"
#include "Square.h"
#include <malloc.h>

PTRFUN squareMethods[]={
	(doubleFuncPtr) value_at_Sqr,
	(doubleFuncPtr) negative_value_at,

};

Square* new_Square(int lb, int ub){
	Square *sqr=(Square *)malloc(sizeof(Square));
	sqr->table=squareMethods;
	sqr->lowerBound=lb;
	sqr->upperBound=ub;
	return sqr;
}

double value_at_Sqr(UnaryFunction* self,double x){
	return x*x;
}

