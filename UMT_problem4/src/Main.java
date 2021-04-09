import java.util.Arrays;

public class Main {
    public static int ChangesForStrongPassword(String string)
    {
        char[] chars = string.toCharArray();
        System.out.println("Parola initiala: "+string+" cu lungimea "+chars.length);
        int changes=0;//contorul care va fi returnat la final

        //verificam mai intai daca avem lowercase uppercase digit pentru a stii ce trebuie sa adaugam
        int lowercase=0;int uppercase=0;int digit=0;
        int codascii;
        int numar_charuri=chars.length;
        for(int i=0;i<chars.length;i++)
        {
            codascii=chars[i];
            if(codascii>=97 && codascii<=122){//este lowercase
                lowercase=1;
            }
            if(codascii>=65 && codascii<=90){//este uppercase
                uppercase=1;
            }
            if(codascii>=48 && codascii<=57){//este digit
                digit=1;
            }
        }
        numar_charuri=numar_charuri+lowercase+uppercase+digit;
        //deoarece acesta este numarul maxim de elemnte din string
        //pentru ca putem sa avem maxim 20 de caractere, putem sa inseram in cazul in care numar_charuri<20
        if(numar_charuri<20)//atunci putem insera maxim 3 elemente
        {
            //acum trebuie sa adaugam toate tipurile de caracter pe care nu le avem
            //adaugam caracterele pe care nu le avem pe pozitiile de tipul aaa
            //in loc de ultimul 'a' vom insera ce ne lipseste
            int j;
            int ok;
            int count;
            int primele_3;
            for (int i = 0; i < chars.length - 1; i++) {//parcurgem fiecare caracter
                j = i + 1;
                count = 1;
                ok = 1;
                primele_3 = 1;
                while (j != chars.length && ok == 1 && primele_3 != 3) {
                    //parcurgem toate caracterele dupa caracterul de pe pozitia i
                    primele_3++;
                    if (chars[i] == chars[j]) {//verificam daca 2 caractere consecutive sunt identice
                        count++;
                    }
                    if (count == 3) {//daca sunt 3 caractere consecutive identice
                        ok = 0;//trebuie sa adaugam un caracter ce ne lipseste
                        //facem aici schimbarea

                        if (lowercase == 0 && ok == 0) {
                            //adaugam un lowercase
                            string = string.substring(0, j) + "a" + string.substring(j);
                            chars = string.toCharArray();
                            changes++;
                            ok = 1;
                            lowercase = 1;
                        }
                        if (uppercase == 0 && ok == 0) {
                            //adaugam un uppercase
                            string = string.substring(0, j) + "A" + string.substring(j);
                            chars = string.toCharArray();
                            changes++;
                            ok = 1;
                            uppercase = 1;
                        }
                        if (digit == 0 && ok == 0) {
                            //adaugam un digit
                            string = string.substring(0, j) + "1" + string.substring(j);
                            chars = string.toCharArray();
                            changes++;
                            ok = 1;
                            digit = 1;
                        }
                    }
                    j++;
                }
            }
            //daca nu avem lowercase uppercase sau digit adaugam la final
            if (lowercase == 0) {
                //adaugam un lowercase
                string = string+ "a";
                chars = string.toCharArray();
                changes++;
            }
            if (uppercase == 0) {
                //adaugam un uppercase
                string = string+ "A";
                chars = string.toCharArray();
                changes++;
            }
            if (digit == 0) {
                //adaugam un digit
                string = string + "1";
                chars = string.toCharArray();
                changes++;
            }
        }
        else //if(numar_charuri>20) deci trebuie sa inlocuim nu sa inseram
        {
            int j;
            int ok;
            int count;
            int primele_3;
            //vom parcurge caracterele sa vedem daca avem triplete de forma aaa si vom inlocui acolo prima data
            for (int i = 0; i < chars.length - 1; i++) {//parcurgem fiecare caracter
                j = i + 1;
                count = 1;
                ok = 1;
                primele_3 = 1;
                while (j != chars.length && ok == 1 && primele_3 != 3) {
                    //parcurgem toate caracterele dupa caracterul de pe pozitia i
                    primele_3++;
                    if (chars[i] == chars[j]) {//verificam daca 2 caractere consecutive sunt identice
                        count++;
                    }
                    if (count == 3) {//daca sunt 3 caractere consecutive identice
                        ok = 0;//nu mai este STRONG
                        //facem aici schimbarea
                        if (lowercase == 0 && ok == 0) {
                            //adaugam un lowercase
                            string = string.substring(0, j) + "a" + string.substring(j+1);
                            chars = string.toCharArray();
                            changes++;
                            ok = 1;
                            lowercase = 1;
                        }
                        if (uppercase == 0 && ok == 0) {
                            //adaugam un uppercase
                            string = string.substring(0, j) + "A" + string.substring(j+1);
                            chars = string.toCharArray();
                            changes++;
                            ok = 1;
                            uppercase = 1;
                        }
                        if (digit == 0 && ok == 0) {
                            //adaugam un digit
                            string = string.substring(0, j) + "1" + string.substring(j+1);
                            chars = string.toCharArray();
                            changes++;
                            ok = 1;
                            digit = 1;
                        }
                    }
                    j++;
                }
            }
            //daca nu avem lowercase uppercase sau digit vom da replace la primele caractere
            int k=0;
            if (lowercase == 0) {
                //adaugam un lowercase
                //string = "a"+string.substring(k,string.length()-1);
                chars[k] = 'a';
                string=String.valueOf(chars);
                changes++;
                k++;
            }
            if (uppercase == 0) {
                //adaugam un uppercase
                chars[k] = 'A';
                string=String.valueOf(chars);
                changes++;
                k++;
            }
            if (digit == 0) {
                //adaugam un digit
                chars[k] = '1';
                string=String.valueOf(chars);
                changes++;
            }
        }
        //System.out.println(chars.length);
        //verificam daca lungimea este potrivita
        //>=6 && <=20
        while(chars.length<6)
        {
            //trebuie sa inseram pentru a ajunge la 6 caractere
            int ascii=chars[chars.length-1];
            if(ascii < 126) { //caracterul 127 in ascii este DEL
                ascii=chars[chars.length-1]+1;
                string=string+(char)ascii;
            }
            else{
                ascii =chars[chars.length-1]-1;
                string=string+(char)ascii;
            }
            chars = string.toCharArray();
            changes++;
        }
        if(chars.length>20)
        {
            int j;
            int ok;
            int count;
            int primele_3;
            //prima data vom cauta triplete de forma aaa
            //si vom sterge din acestea un caracter
            int i = 0; j=0;
            while(i<chars.length-1 && chars.length>20 && j<chars.length){

                int del=0;
                j = i + 1;
                count = 1;
                ok = 1;
                primele_3 = 1;
                while (j != chars.length && ok == 1 && primele_3 != 3) {
                    //parcurgem toate caracterele dupa caracterul de pe pozitia i
                    primele_3++;
                    if (chars[i] == chars[j]) {//verificam daca 2 caractere consecutive sunt identice
                        count++;
                    }
                    if (count == 3)
                    {
                        //daca sunt 3 caractere consecutive identice
                        ok = 0;//nu mai este STRONG
                        //stergem
                        string = string.substring(0, j) + string.substring(j+1);
                        chars = string.toCharArray();
                        changes++;
                        del=1;
                    }
                    j++;
                }
                if(del==0) //daca stergem ramanem pe aceeasi pozitie
                {
                    i++;
                }
            }
            //daca lungimea este mai mare de 20 vom sterge caractere de la final
            while(chars.length>20)
            {
                string = string.substring(0, string.length()-1);
                chars=string.toCharArray();
                changes++;
            }
        }
        //verificam daca inca contine 3 caractere consecutive identice
        int j;int ok=1;int count;int ascii;int ascii2;int primele_3;
        for(int i=0;i<chars.length-1;i++){//parcurgem fiecare caracter
            j=i+1;
            count=1;
            ok=1;
            primele_3=1;
            while(j!=chars.length && ok==1 && primele_3!=3){
                //parcurgem toate caracterele dupa caracterul de pe pozitia i
                primele_3++;
                if(chars[i]==chars[j]){//verificam daca 2 caractere consecutive sunt identice
                    count++;
                }
                if(count==3){//daca sunt 3 caractere consecutive identice
                    ok=0;//nu mai este STRONG
                    //facem aici schimbarea
                    //modificam ultimul caracter din aa(a)bb si il inlocuim considerand
                    //ca nu trebuie sa fie identic cu a si nici cu caracterul b
                    ascii =chars[j]; //ii luam valoarea in ascii
                    if(ascii < 126) { //caracterul 127 in ascii este DEL
                        ascii =chars[j]+1;
                        //Verificam daca valoarea caracterului pe care il modificam este identica
                        // cu valoarea caracterului de dupa el
                        if(j!=chars.length-1)
                            if((char)ascii==chars[j+1]) {
                                ascii++;
                            }
                        chars[j]=(char)ascii;//marim valoarea acestuia
                    }
                    else{
                        ascii =chars[j]-1;
                        if((char)ascii==chars[j+1]) {
                            ascii--;
                        }
                        chars[j]=(char)ascii;//scadem valoarea acestuia
                    }
                    changes++;
                }
                j++;
            }
        }
        //iar in cele din urma afisam
        System.out.print("Parola schimbata: ");
        System.out.print(chars);
        System.out.println(" cu lungimea "+chars.length);

        return changes;
    }
    public static void main(String args[])
    {
        String string = "aaabbbbbbcccddddddddcccccc"; //26 de caractere
        System.out.println("Numarul minim de schimbari este: "+ChangesForStrongPassword(string)+"\n");

        String string2 = "!";
        System.out.println("Numarul minim de schimbari este: "+ChangesForStrongPassword(string2)+"\n");

        String strong = "aaBcD1";
        System.out.println("Numarul minim de schimbari este: "+ChangesForStrongPassword(strong)+"\n");

        String string3 = "aaabbxbbbcxcddxdddddcxcccccc"; //28 de caractere
        System.out.println("Numarul minim de schimbari este: "+ChangesForStrongPassword(string3)+"\n");

        String string4 = "zxcvbnmasdfghjklqwertyuiop"; //24 de caractere
        System.out.println("Numarul minim de schimbari este: "+ChangesForStrongPassword(string4)+"\n");

        String string5 = "111a";
        System.out.println("Numarul minim de schimbari este: "+ChangesForStrongPassword(string5)+"\n");


    }
}

