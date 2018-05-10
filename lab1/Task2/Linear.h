#include "Unary_Function.h"

typedef struct{
	PTRFUN *table;
	int lowerBound;
	int upperBound;
	double a;
	double b;
} Linear;


Linear* new_Linear(int lb, int ub, double a_coef, double b_coef);
double value_at_Lin(UnaryFunction* self,double x);