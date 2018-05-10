#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>

// Pomocne strukture
typedef char const* (*PTRFUN)();


//Razred Animal--------------------------------
typedef struct {
	PTRFUN *table;
	char *name;
}Animal;

createAnimal(Animal* animal,char* name);
void animalPrintGreeting(Animal* animal);
void animalPrintMenu(Animal* animal);

PTRFUN table_anim[2]={
	(PTRFUN) NULL,
	(PTRFUN) NULL
};

createAnimal(Animal* animal,char *name){
	animal->table= table_anim;
	animal->name=name; 
}

void animalPrintGreeting(Animal* animal){
	printf("%s pozdravlja: %s \n", animal->name, (const char*)animal->table[0]());
}

void animalPrintMenu(Animal* animal){
	printf("%s voli  %s \n", animal->name, (const char*)animal->table[1]());
}


//---------------------------------------------

//Razred Dog-----------------------------------
typedef struct{
	Animal parent;
} Dog;

createDog(Dog* animal,char* name);
const char* dogGreeting(void);
const char* dogMenu(void);

PTRFUN table_dog[2]={
	(PTRFUN) dogGreeting,
	(PTRFUN) dogMenu
};

createDog(Dog* dog,char* name){
	PTRFUN* table=(PTRFUN*)(*(unsigned int*)dog);
	createAnimal((Animal*)dog,name);
	table=table_dog;
}

const char* dogGreeting(void){
	return "vau!";
}

const char* dogMenu(void){
	return "kuhanu govedinu";
}

//---------------------------------------------


//Razred cat-----------------------------------
typedef struct{
	Animal parent;
} Cat;

createCat(Cat* animal, char* name);
const char* catGreeting(void);
const char* catMenu(void);

PTRFUN table_cat[2]={
	(PTRFUN) catGreeting,
	(PTRFUN) catMenu
};

createCat(Cat* cat,char* name){
	PTRFUN* table=(PTRFUN*)(*(unsigned int*)cat);
	createAnimal((Animal*)cat,name);
	table=table_cat;
}

const char* catGreeting(void){
	return "mijau!";
}

const char* catMenu(void){
	return "konzerviranu tunjevinu";
}

//---------------------------------------------


//Function declarations
void testAnimals(void);

char *s[3]={
	"Doge",
	"Woofi",
	"Brek"
};

Dog* dogList(int numDogs){
	int i;
	Dog *dogs=(Dog*)malloc(sizeof(Dog)*numDogs);
	

	for(i =0;i<numDogs;i++){
		createDog(dogs+i,s[i]);
	}
	return dogs;
}

void testDoge(){
	int i;
	Dog*list=dogList(3);
	for(i=0;i<3;i++){
		printf("%s \n", ((Animal*)(list+i))->name );
	}
}

//Program
int main(int argc, char *argv[]){
   //testAnimals();
   testDoge();
   return;
}

void testAnimals(void){
	//Pt.1: Initialisation
	Animal* p1=(Animal*)malloc(sizeof(Dog));
	createDog((Dog*)p1,"Hamlet");

	Animal* p2=(Animal*)malloc(sizeof(Cat));
	createCat((Cat*)p1,"Ofelija");

	Animal* p3=(Animal*)malloc(sizeof(Dog));
	createDog((Dog*)p1,"Polonije");

	//Pt.2: Greeting
	animalPrintGreeting(p1);
	animalPrintGreeting(p2);
	animalPrintGreeting(p3);
	
	//Pt.3: Menu
	animalPrintMenu(p1);
	animalPrintMenu(p2);
	animalPrintMenu(p3);
	
	//Pt.4: Deallocation
	free(p1); free(p2); free(p3);
}


