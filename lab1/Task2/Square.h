#include "Unary_Function.h"

typedef struct{
	PTRFUN *table;
	int lowerBound;
	int upperBound;
} Square;

Square *new_Square(int lb, int ub);

double value_at_Sqr(UnaryFunction* self,double x);