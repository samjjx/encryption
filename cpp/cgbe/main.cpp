#include <iostream>
#include "cgbe.h"
using namespace std;

class TestCrypto{
 public:
  TestCrypto() {
    CGBE* cgbe = new CGBE();
    mpz_t a, b, c;
    mpz_init(a);
    mpz_init(b);
    mpz_init(c);

    cgbe->setvalue(a, cgbe->encoding);
    cgbe->setvalue(b, cgbe->encoding);
    cgbe->setvalue(c, cgbe->encoding);

    cgbe->encrypt(a, a);
    cgbe->encrypt(b, b);
    cgbe->encrypt(c, c);

    int i = 0;
    for (; i < 100000; i++) {
      cgbe->add(a, a, b);
    }

    cgbe->decrypt(a, a, 1);
    gmp_printf("test result: %Zd \n", a);
  }
};

int main() {
	TestCrypto* cryp = new TestCrypto();
    return 0;
}