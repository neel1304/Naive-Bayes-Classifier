import java.util.*;
import java.io.*;
import java.util.ArrayList;
class Naive_Bayes
{
    static int wordcount(String k)  
        {  
          int c=0;  
      
            char ch[]= new char[k.length()];     
            for(int i=0;i<k.length();i++)  
                {  
                    ch[i]= k.charAt(i);  
                    if( ((i>0)&&(ch[i]!=' ')&&(ch[i-1]==' ')) || ((ch[0]!=' ')&&(i==0)) )  
                    c++;  
            }  
            return c;  
        }
        static int uniquewordcount(String k2)
        {
            String[] words = k2.split(" ");
            boolean[] arr = new boolean[words.length];
            int i,j = 0;
            int count = 0;
            for (i = 0; i < words.length; i++)
            {
                if (!arr[i]) 
                {
                    count++;
                        for (j = i + 1; j < words.length; j++)
                        {
                            if (words[j].compareTo(words[i]) == 0) 
                                {
                                    arr[j] = true;
                                    count--;
                                }
                        }
                }
            }
        return count;
       }
    
    public static void main(String args[])throws IOException
    {
        Scanner sc=new Scanner(System.in);
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("CREATE DATA SET");
        System.out.println("---------------");
        System.out.println("Enter Number of Datas");
        int n=sc.nextInt();
        String arr[]=new String[n];
        String cat[]=new String[n];
        System.out.println("Enter the two Categories");
        String str1=br.readLine();
        String str2=br.readLine();
        
        List<Integer>positions = new ArrayList<>();//To Store the positions of Category
        List<Integer>positions2 = new ArrayList<>();//To store the positions of Cat 2
        List<String>naive = new ArrayList<>();//Cat 1 strings
        List<String>bayes = new ArrayList<>();//cat 2
        List<Integer>len = new ArrayList<>();
        List<Integer>lens= new ArrayList<>();
        System.out.println("Enter the String");
        for(int i=0;i<n;i++)
        {
            arr[i]=br.readLine();
        }
        System.out.println("Enter the Category");
        for(int i=0;i<n;i++)
        {
            cat[i]=br.readLine();
        }
        System.out.println();
        System.out.println("----String----");
        for(int i=0;i<n;i++)
        {
            System.out.println(arr[i]);

        }
        System.out.println("----Category--");
        for(int i=0;i<n;i++)
        {
            System.out.print(cat[i]);
            System.out.println();
        }
        
        System.out.println("Input Test Data");
        String s=br.readLine();//Test Data
        int word=wordcount(s);//Number of Words in Test Data...2
        int len2=0;
        
        for(int i=0;i<n;i++)
        {
            if(cat[i].equalsIgnoreCase(str1)==true)
            {       
               positions.add(i);//Adding Positions where Cat is String1CAT...0 and 2
            }
        }
        for(int i=0;i<n;i++)
        {
            if(cat[i].equalsIgnoreCase(str2)==true)
            {
                positions2.add(i);//...1 and 3
            }
        }
        int total=positions.size()+positions2.size();
        for(int i=0;i<positions.size();i++)
        {
                len.add(wordcount(arr[positions.get(i)]));//Number of Words in each string
                naive.add(arr[positions.get(i)]);//Cat 1 Strings
        }
        for(int i=0;i<positions2.size();i++)
        {
                lens.add(wordcount(arr[positions2.get(i)]));//Number of Words of Cat 2 strings
                bayes.add(arr[positions2.get(i)]);//Cat 2 Strings    
        }
 
        int sum1=0;
        int sum2=0;
        for(int i=0;i<len.size();i++)
        {
            sum1+=len.get(i);//Sum of Lengths of All String.
        }
        for(int i=0;i<lens.size();i++)
        {
            sum2+=lens.get(i);
        }
        
        //For Unique Words in Data set
        int sum=0;
        for(int i=0;i<n;i++)
        {
            sum+=uniquewordcount(arr[i]);
        }
        
        /*for(int i=0;i<len.size();i++)
        {
            System.out.println("Len ka="+len.get(i)+" ");
        }
        for(int i=0;i<lens.size();i++)
        {
            System.out.println("Lens ka="+lens.get(i)+" ");
        }*/
        
        
        /*for(int i=0;i<positions.size();i++)
        {
            System.out.print("CAT 1 positions are ="+positions.get(i)+" ");
        }
        System.out.println();
        for(int i=0;i<positions2.size();i++)
        {
            System.out.print("CAT 2 positions are ="+positions2.get(i)+" ");
        }
       */
        
            
            
        System.out.println("Number of Words = "+word);
        System.out.println("Number of Words in Category 1 = "+sum1);
        System.out.println("Number of Words in Category 2 = "+sum2);
        System.out.println("Number of Unique Words = "+sum);
        
        
        String[]word2=s.split(" ");//Separate Words
        int lenk=word2.length;
        int prob1=1;
        int prob2=1;
        double prob11[]=new double[lenk];
        double prob22[]=new double[lenk];
        int countc=0;
        
        
        
        
        for(int i=0;i<lenk;i++)
        {
            for(int j=0;j<naive.size();j++)
            {
                if(word2[i].equalsIgnoreCase(naive.get(j)))
                countc++;
            }
        }
        System.out.println("Number of times =" + countc);
        for(int i=0;i<lenk;i++)
        {
            prob11[i]=((countc+1)/(sum1+sum));
        }
        countc=0;
        for(int i=0;i<lenk;i++)
        {
            for(int j=0;j<bayes.size();j++)
            {
                if(word2[i].equalsIgnoreCase(bayes.get(j)))
                countc++;
            }
        }
        
        for(int i=0; i < lenk;i++)
        {
            prob22[i]=((countc+1)*1.0)/((sum2+sum)*1.0);
        }
        countc=0;
        
        double prob111=1.0;
        double prob222=1.0;
        for(int i=0;i<lenk;i++)
        {
            prob111*=prob11[i];
            prob222*=prob22[i];
        }
        System.out.println("Prob1 is = "+prob111);
        System.out.println("Prob2 is = "+prob222);
        if(prob111>prob222)
        System.out.println(s+" belongs to "+str2+" category");
        else
        System.out.println(s+" belongs to "+str1+" category");
    }
        
        
        
    }
