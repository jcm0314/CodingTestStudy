#include <stdio.h>
#define LEN_INPUT 10

int main(void) {
    
    char s1[LEN_INPUT];
    scanf("%s", s1);
    for(int i = 0; i <=10; i++){
    if( s1 >= 'A' && s1 <= 'Z'){
        printf("%s", s1+32);
    } else if( s1 >= 'a' && s1 <= 'z'){
        printf("%s", s1-32);
    
    }
        else printf("")
    }

    return 0;
}
