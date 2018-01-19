#include <iostream>
using namespace std;

class Obj { 
 
  private:
	string m_s;

  public:
	Obj(string str):m_s(str){
	  cout << m_s << " created." << endl;
	 }
	Obj (const Obj& another) { 
	  m_s = another.m_s + " clone";
	  cout << m_s << " created." << endl;
	}
	~Obj() { 
	 cout << m_s << " destroyed." << endl;
	}


};

int main() {

  Obj obj1 = Obj("obj1");
  Obj *obj4 = new Obj("obj4");
  Obj obj2 = obj1;
  Obj obj3("obj3");
  Obj obj5(*obj4);
  delete obj4;
  return 0;


}
