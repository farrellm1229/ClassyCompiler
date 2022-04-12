# Classy Compiler
Professor Labouseur, please grade the branch titled "Project3". 

I have created several test cases labeled sem#.txt and have described in the comments of the text files whether or not the programs will pass and why.

/*Things I did not add*/
I used a linked hashmap for my symbol table, and in cases where variables are being redefined within a new scope, a duplicate key is created. Duplicate keys are not allowed in linked hash maps, so in the case for
{int a
a=1
{string a
a="hi}
}$
there is only one instance of a in the symbol table. This is something I know how to fix but I just did not get around to doing so.

Thank you!
