import java.util.Scanner;
import java.util.*;
import java.io.*;


public class SpellChecker implements SpellCheckerInterface{ 
    private File inFile;
    private HashSet<String> hashSet;
    Set<String> setSuggestions;
    
    
    public SpellChecker(String fileName) throws FileNotFoundException { 
        File inFile = new File(fileName);
        hashSet = new HashSet<String>();
        Scanner input = new Scanner(inFile);
        setSuggestions = new HashSet<String>();
        
    
     while (input.hasNextLine()){
         hashSet.add(input.nextLine().toLowerCase().replaceAll("\\p{Punct}",""));
        }
        input.close();
    }
    
    public List<String> getIncorrectWords(String filename) {
        
        List<String> returnList = new ArrayList<String>();
        try{
            File inFile = new File(filename);
            Scanner input2 = new Scanner(inFile);
            
            ArrayList<String> inputTxt = new ArrayList<String>();
           
            while(input2.hasNext()){
                inputTxt.add(input2.next().toLowerCase().replaceAll("\\p{Punct}",""));
            }
            
            for(String testTxt: inputTxt){
                System.out.println(testTxt);
                int currentSize=0;
                boolean match = false;
                for(String word: hashSet){
                    currentSize++;
                    if(testTxt.compareTo(word)==0){
                        match=true;
                    }
                    else if (currentSize==hashSet.size() && match==false){ 
                        returnList.add(testTxt);
                    }
                }
            }
        }
        catch(FileNotFoundException e){
           System.out.println("Please try again with correct input file name");
           System.out.println(e);
        }
        return returnList;
    }
    
    public Set<String> getSuggestions(String word){
        
        ArrayList<String> set = new ArrayList<String>();
        char c;
        for(int i = 0; i<word.length()+1; i++){
            for(c = 'a'; c <= 'z'; ++c){
                StringBuilder sb = new StringBuilder(word);
                sb.insert(i, c);
                set.add(sb.toString());
            }
         }
        
        for(int i = 0; i<word.length(); i++){
            StringBuilder str = new StringBuilder(word);
            str.deleteCharAt(i);
            set.add(str.toString());
        }
        
        for(int i=0; i<word.length()-1;i++){
            StringBuilder swap = new StringBuilder(word);
            char k = word.charAt(i); 
            char j = word.charAt(i+1);
            swap.deleteCharAt(i);
            swap.deleteCharAt(i);
            swap.insert(i, j);
            swap.insert(i+1, k);
            set.add(swap.toString());
        }
        
        dictCompare(set);
        
        return setSuggestions;
    }    
    //set is suggested words, hashset is dictionary
    // setSuggestions is a set returning suggestions
    private Set<String> dictCompare(ArrayList<String> set){

          for(String test: set){
              if(hashSet.contains(test)){
                      setSuggestions.add(test);
                    }
          }
        return setSuggestions;
    }
}

