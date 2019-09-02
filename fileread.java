import java.util.*;
import java.io.*;
import java.sql.*;
  class fileread
  {
        public static Connection con;
        public static PreparedStatement st;
        public static ResultSet rs;

    public fileread() {
        con=null;
        rs=null;
        st=null;
      }
    public static void main()throws IOException
    {
        String stmt;
      
      try{
            
        File f=new File("D:\\test.txt");
        String a[] ;
        Scanner ob=new Scanner(f);
        List<String> s=new ArrayList<String>();
        while(ob.hasNextLine())
        {
            String st=ob.nextLine();
            a=st.split("[ ,.?@]+");
            
            for(String i:a)
            {
                s.add(i);
            }
        }
            // System.out.println(s);
         try{
          String url="jdbc:mysql://localhost:3306/mydb";
            String uname="root";
            String pass="";
            Class.forName("com.mysql.jdbc.Driver");
            
             con=DriverManager.getConnection(url,uname,pass);
               
            for(int j=0;j<s.size();j++)
            {
               
                /*PreparedStatement*/ st=con.prepareStatement("INSERT INTO collection VALUES(?)"); 
                st.setString(1,s.get(j));
                st.executeUpdate();
            
            }
        }
        catch(Exception ee)
        {
        System.out.println("problem ");
         }
    
        Scanner obj=new Scanner(System.in);
         System.out.println("Enter the word to be searched");
         String find=obj.next();
         try{
         rs=st.executeQuery("SELECT word FROM collection WHERE word like'"+find+"%'");
         String search=null;
         //boolean search=bs(str,find);
         while(rs.next()){
                search=rs.getString("word");
                
            }
         if(search==null)
         System.out.println("word not present");
         else
         System.out.println("word present");
        }
        catch(Exception e2)
        {
           System.out.println("ERROR : "+e2); 
        }
       }
       catch(IOException e)
       {
       System.out.print(e.getMessage());
       }
     }
  }

    


