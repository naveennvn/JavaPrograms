import java.util.*;
class FindAnagramPresent {
  public static void findAnagramPresentornot() {
    String str = "bcdcbababd";
    String pattern = "bacba";
    HashMap<Character,Integer> map=new HashMap<>();
    int start=0,end=0,matchedCount=0;
    for(int i=0;i<pattern.length();i++){
        map.put(pattern.charAt(i),map.getOrDefault(pattern.charAt(i),0)+1);
    }
    while(end<str.length())
    {
      char endChar=str.charAt(end);
      if(map.containsKey(endChar))
      {
        map.put(endChar,map.get(endChar)-1);
        if(map.get(endChar)==0){
          matchedCount++;
        }
      }
      if(matchedCount==map.size())
      {
        System.out.println("matchedCount: "+matchedCount+" String is: "+str.substring(start,end+1));
      }
      if(end>=pattern.length()-1)
      {
        char startChar=str.charAt(start++);
        if(map.containsKey(startChar))
        {
          if(map.get(startChar)==0)
          {
            matchedCount--;
          }
          map.put(startChar,map.get(startChar)+1);
        }
      }
      end++;
    }
  }

  public static void main(String args[]) {
    findAnagramPresentornot();
  }
}