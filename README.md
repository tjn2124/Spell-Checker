# Spell-Checker
A spell checker using a hash table

The spell checker object checks for spelling errors in an input file, 
referencing a provided dictionary file words.txt.
The SpellChecker object will use this dictionary as a reference when checking
for spelling errors in a specified input file. Every word is converted to lowercase
and all punctuation is removed before inserting it into the HashSet. 
getIncorrectWords(String filename) - This method getIncorrectWords() returns a 
list of all words in the input file that are incorrectly spelled according to 
the dictionary. 
