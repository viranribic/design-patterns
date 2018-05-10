#ifndef UNARY_FUNCTION_H
#define UNARY_FUNCTION_H

typedef void const* (*PTRFUN)(void);

typedef struct {
	PTRFUN* table;
	int lowerBound;
	int upperBound;
} UnaryFunction ;

typedef double (*doubleFuncPtr)(UnaryFunction*, double);

UnaryFunction* new_UnaryFunction(int lb, int ub);
//double value_at(UnaryFunction* self,double x);
double negative_value_at(UnaryFunction* self,double x);
void tabulate(UnaryFunction* self);
int same_functions_for_ints(UnaryFunction* f1, UnaryFunction*f2, double tolerance);

#endif 