import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/*It is about finding the longest substring without having any duplicates from a string 
 * it uses sliding window [a,b] using HashSet and other collections
 * Reverse only vowels in a string and print*/

public class SlidingWindowPrograms {

  /*
   * It is about finding the longest substring without having any duplicates from
   * a string
   */
  public static void findLengthofSubstr() {
    int start, end;
    String str = "bcfxydcbabab";// dcba
    start = end = 0;
    HashSet<Character> set = new HashSet<>();
    String result = "";
    StringBuilder substr = new StringBuilder(" ");
    int n = str.length();
    while (start < n && end < n) {
      substr.setLength(0);
      substr.append(str.substring(start, end + 1));
      set.clear();
      for (char ch : substr.toString().toCharArray())
        set.add(ch);
      if (set.size() < substr.length())
        start = start + 1;
      else {
        if (result.length() < substr.length())
          result = substr.toString();
      }

      end++;
    }
    System.out.println("largest substring is:" + result);
  }

  public static void lengthOfLongestSubstring() {
    String s = "pipuqlwwkuabcdef";
    int n = s.length(), ans = 0;
    Map<Character, Integer> map = new HashMap<>(); // current index of character
    // try to extend the range [i, j]
    for (int j = 0, i = 0; j < n; j++) {
      if (map.containsKey(s.charAt(j))) {
        i = Math.max(map.get(s.charAt(j)), i);
      }
      ans = Math.max(ans, j - i + 1);
      map.put(s.charAt(j), j + 1);
    }
    System.out.println(map);

    // To sort the HashMap based on value using comparator
    map.entrySet().stream().sorted(Entry.comparingByValue()).forEach(x -> System.out.print(x.getKey()));
    // To collect the sorted function to a HashMap follow like this Stream follows
    // like this
    /*
     * 1.Stream 2.Operation[Range,Sort,Filter,map]
     * 3.Terminatefunc[Collection,foreach]
     */
    Map<Character, Integer> sortedMap = map.entrySet().stream().sorted(Entry.comparingByValue())
        .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    System.out.println(sortedMap);
    // Entry also a type like integer string List<Entry<String,Integer>> list=new
    // LinkedList();

  }

  public static void reverseVowelsfromString() {
    String s = "leetcodU";
    Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
    char arr[] = s.toCharArray();
    int left = 0;
    int right = arr.length - 1;
    while (left < right) {
      if (!set.contains(arr[left])) {
        left++;
      } else if (!set.contains(arr[right])) {
        right--;
      } else {
        char tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
        left++;
        right--;
      }
    }
    System.out.println(new String(arr));

  }

  public static String reversepreserveSpace(String str) {
    int n = str.length();
    int a_ptr = 0;
    int b_ptr = n - 1;
    String arr[] = str.split("");
    // System.out.println(Arrays.toString(arr));
    while (a_ptr < b_ptr) {
      if (arr[a_ptr].equals(" ")) {
        a_ptr++;
        continue;
      } else if (arr[b_ptr].equals(" ")) {
        b_ptr--;
        continue;
      } else {
        String temp = arr[a_ptr];
        arr[a_ptr] = arr[b_ptr];
        arr[b_ptr] = temp;
        a_ptr++;
        b_ptr--;
      }
    }
    System.out.println(Arrays.toString(arr));
    String temp = String.join("", arr);
    System.out.println(temp);
    return temp;
  }

  public static void findLargeCommonSubstring() {
    String arr[] = { "grace", "aceful", "disgraceful", "gracefully" };
    String str = arr[0];
    int n = str.length();
    int a_ptr = 0, b_ptr = 0;
    String res = " ";
    while (a_ptr <= n) {
      b_ptr = a_ptr + 1;
      while (b_ptr <= n) {
        String stem = str.substring(a_ptr, b_ptr);
        int k = 1;
        for (k = 1; k < arr.length; k++) {
          if (!arr[k].contains(stem))
            break;
        }
        b_ptr++;
        if (k == arr.length && res.length() < stem.length())
          res = stem;
      }
      a_ptr++;
    }
    System.out.println(res);
  }

  public static boolean findAnagramPresentor() {
    String str = "bcdcbababd";
    String pattern = "abc";
    HashMap<Character, Integer> map = new HashMap<>();
    int start = 0, end = 0, matchedElement = 0;

    for (int i = 0; i < pattern.length(); i++)
      map.put(pattern.charAt(i), map.getOrDefault(pattern.charAt(i), 0) + 1);

    for (end = 0; end < str.length(); end++) {
      char currentChar = str.charAt(end);
      if (map.containsKey(currentChar)) {
        map.put(currentChar, map.get(currentChar) - 1);
        if (map.get(currentChar) == 0)
          matchedElement++;
      }
      if (map.size() == matchedElement) {
        System.out.println(str.substring(start, end + 1));
      }
      if (end >= pattern.length()-1) {
        char startChar = str.charAt(start++);
        if (map.containsKey(startChar)) {
          if (map.get(startChar) == 0)
            matchedElement--;
          map.put(startChar, map.get(startChar) + 1);
        }

      }
    }

    return false;
  }

  public static String findSmallestString() {
    String str = "badeabcaae";
    String pattern = "aabc";
    if (str == null || str.length() == 0 || pattern == null || pattern.length() == 0) {
      return "";
    }

    Map<Character, Integer> map = new HashMap<>();
    int matchedElement = 0;
    int start = 0;
    int minSubstringLegth = Integer.MAX_VALUE;
    int minSubStringStart = 0;

    for (int i = 0; i < pattern.length(); i++) {
      map.put(pattern.charAt(i), map.getOrDefault(pattern.charAt(i), 0) + 1);
    }

    for (int end = 0; end < str.length(); end++) {
      char currentChar = str.charAt(end);

      if (map.containsKey(currentChar)) {
        map.put(currentChar, map.get(currentChar) - 1);

        if (map.get(currentChar) >= 0) {
          matchedElement++;
        }
      }

      while (matchedElement == pattern.length()) {
        char charAtStart = str.charAt(start++);
        if (map.containsKey(charAtStart)) {
          if (map.get(charAtStart) == 0) {
            matchedElement--;
          }

          map.put(charAtStart, map.get(charAtStart) + 1);
        }
        if (end - start + 1 < minSubstringLegth) {
          minSubstringLegth = end - start + 1;
          minSubStringStart = start;
        }
      }

    }

    if (minSubstringLegth > str.length()) {
      return "";
    }

    return str.substring(minSubStringStart, minSubStringStart + minSubstringLegth);
  }

  public static void main(String[] args) {
    // findLengthofSubstr();
    //System.out.println(findAnagramPresentor());
    //it interchanges the vowels from first to last and last to first.  
    // reverseVowelsfromString();
    // findLargeCommonSubstring();
    // System.out.println(reversepreserveSpace("Hari Krishna Mannava i"));

  }

}
