#include <iostream>

#include "UTF8string.hpp"

using namespace std;


static void func(UTF8string u) {
    // Function to make sure that nothing crashes
    cout << "Testing operator !: " << u << " -> " << !u << endl;
}




int main(int argc, char **argv) {
    //
    // Expected output:
    // ---------------
    // test contains: Mais où sont les neiges d'antan?
    // length in bytes of test: 33
    // number of characters (one 2-byte character): 32
    // position of "sont": 9
    // test2 before replacement: Всё хорошо́, что хорошо́ конча́ется
    // test2 after replacement: Всё просто, что просто конча́ется
    // test + test2: Mais où sont les neiges d'antan?Всё просто, что просто конча́ется
    // Appending !!! to test
    // Result: Mais où sont les neiges d'antan?!!!
    // Testing operator *: hip hip hip hurray
    // Testing operator !: Никола́й Васи́льевич Го́голь -> ьлоѓоГ чивеьл́исаВ й́алокиН
    //
    UTF8string test = UTF8string("Mais où sont les neiges d'antan?");
    UTF8string test2 = UTF8string("Всё хорошо́, что хорошо́ конча́ется");
    UTF8string test3("hip ");
    UTF8string test4("Никола́й Васи́льевич Го́голь");
    cout << "test contains: " << test << endl;
    cout << "length in bytes of test: " << test.bytes() << endl;
    cout << "number of characters (one 2-byte character): " << test.length()
                                                            << endl;
    cout << "position of \"sont\": " << test.find("sont") << endl;
    cout << "test2 before replacement: " << test2 << endl;
    test2.replace("хорошо́", "просто");
    cout << "test2 after replacement: " << test2 << endl;
    cout << "test + test2: " << test + test2 << endl;
    cout << "Appending !!! to test" << endl;
    test += UTF8string("!!!");
    cout << "Result: " << test << endl;
    cout << "Testing operator *: " << test3 * 3 << "hurray" << endl;
    cout << "Testing operator *: " << 3 * test3 << "hurray" << endl;
    func(test4);



    test = UTF8string("Mais où sont les neiges d'antan?");
    test2 = UTF8string("Всё хорошо́, что хорошо́ конча́ется");
    UTF8string my_love = UTF8string("我心中的火焰是您给点燃的，所以也请您来吹灭。我一个人的力量是怎么也灭不了的。");
    cout << "test contains: " << test.value() << endl;
    cout << "length in bytes of test: " << test.bytes() << endl;
    cout << "number of characters (one 2-byte character): " << test.length()
                                                            << endl;
    cout << "position of \"sont\": " << test.find("sont") << endl;
    cout << "test2 before replacement: " << test2.value() << endl;
    test2.replace("хорошо́", "просто");
    cout << "test2 after replacement: " << test2.value() << endl;

    std::cout << "my_love contains: " << my_love.value() << endl;
    std::cout << "length in bytes of my_love:" << my_love.bytes()<< endl;
    cout << "number of characters in my_love: " << test.length()
                                                            << endl;
    my_love.replace("您","爱");
    std::cout << "my_love after replacement:" << my_love.value() << endl;
    cout << "position of \"火焰\": " << my_love.find("火焰") << endl;
    my_love.replace("我","我自己");
    std::cout << "my_love after another replacement:" << my_love.value() << endl;
    my_love.replace("的","の");
    my_love.replace("吹","熄");
    std::cout << "my_love after the last replacement:" << my_love.value() << endl;
    func(my_love);
    return 0;
}
