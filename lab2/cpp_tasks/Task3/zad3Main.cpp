#include <iostream>

class AbstractDatabase{
public:
	virtual void getData()=0;
};

class MockDatabase:public AbstractDatabase{
public:
	virtual void getData(){
		std::cout<< "getData from mockDatabase.\n";
	}
};

class ConcreteDatabase:public AbstractDatabase{
public:
	virtual void getData(){
		std::cout<<"getData from concreteDatabase.\n";
	}
};

class Client1{
	ConcreteDatabase myDatabase;
public:
	Client1():
		myDatabase(){}
	public:
		void transaction(){
			myDatabase.getData();
		}
};

class Client2{
	AbstractDatabase& myDatabase;
public:
	Client2(AbstractDatabase& db):
		myDatabase(db){}
public:
	void transaction(){
		myDatabase.getData();
	}
};


int main(){

	MockDatabase* pdb=new MockDatabase();
	Client2 client2(*pdb);
	client2.transaction();
	
	Client1 client1;
	client1.transaction();

}