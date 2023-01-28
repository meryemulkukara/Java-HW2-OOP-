import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	
    //Reads the file and saves the lines into a String[] array.Then returns it
    public static String[] readFile(String path){
        try {
            int i=0;
            int lenght= Files.readAllLines(Paths.get(path)).size();
            String[] results = new String[lenght];
            for (String line : Files.readAllLines(Paths.get(path))) {
                results[i++]=line;
            }
            return results;
            
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void readPeople(String lines,ArrayList<User>Users,ArrayList<Actor>Actors,	ArrayList<Director>Directors,    ArrayList<ChildActor>ChildActors,
    								ArrayList<StuntPerformer>stuntPerformers,    ArrayList<Writer>writers)
    {
    	String [] PeopleArr=lines.split("\t");
    	if(PeopleArr[0].equals("User:"))
    		add_user(PeopleArr[1],PeopleArr[2],PeopleArr[3],PeopleArr[4],Users);
    	else{
    		if(PeopleArr[0].equals("Actor:"))
    			add_actor(PeopleArr[1],PeopleArr[2],PeopleArr[3],PeopleArr[4],PeopleArr[5],Actors);
    		else{
    			if(PeopleArr[0].equals("Director:"))
    				add_director(PeopleArr[1],PeopleArr[2],PeopleArr[3],PeopleArr[4],PeopleArr[5],Directors);
    			else {
    				if(PeopleArr[0].equals("ChildActor:"))
    					add_child_actor(PeopleArr[1],PeopleArr[2],PeopleArr[3],PeopleArr[4],PeopleArr[5],ChildActors);
    				else {
    					if(PeopleArr[0].equals("StuntPerformer:"))
    						add_stunt_performer(PeopleArr[1],PeopleArr[2],PeopleArr[3],PeopleArr[4],PeopleArr[5],PeopleArr[6],stuntPerformers);
    					else {
    						if(PeopleArr[0].equals("Writer:"))
    							add_writer(PeopleArr[1],PeopleArr[2],PeopleArr[3],PeopleArr[4],PeopleArr[5],writers);
    						else
    							System.out.println("This people.txt command is not defined");
    					}	}   }	}  }    	  	
    }
    
 
  //ADD_USER method add line by line id,name,surname,country,height information to User obj
    public static void add_user(String id,String name,String surname,String country,ArrayList<User>Users)
    {   Users.add(new User(id,name,surname,country));   }
    
    //ADD_ACTOR method add line by line id,name,surname,country,height information to Actor obj
    public static void add_actor(String id,String name,String surname,String country,String height,ArrayList<Actor>Actors)
    {    Actors.add(new Actor(id,name,surname,country,height));    }
    
    //ADD_DIRECTOR method add line by line id,name,surname,country,agent information to Director  obj
    public static void add_director(String id,String name,String surname,String country,String agent,ArrayList<Director>Directors)
    {      	Directors.add(new Director(id,name,surname,country,agent));    }
    
    
    //ADD_CHILD_ACTOR method add line by line id,name,surname,country,age information to ChildActor obj
    public static void add_child_actor(String id,String name,String surname,String country,String age,ArrayList<ChildActor>ChildActors)
    {      ChildActors.add(new ChildActor(id,name,surname,country,age)); }
    
    //ADD_STUNT_PERFORMER method add line by line id,name,surname,country,height,ActorIdList information to stuntPerformers obj
    public static void add_stunt_performer(String id,String name,String surname,String country,String height,String ActorIdList,ArrayList<StuntPerformer>stuntPerformers)
    {   
    	String[] ArrActorIDs=ActorIdList.split(",");
    	ArrayList<String> actor_Ids=new ArrayList<String>();
    	for(String arr:ArrActorIDs)
    		actor_Ids.add(arr);
    	stuntPerformers.add(new StuntPerformer(id,name,surname,country,height, actor_Ids));
    }
    
  //ADD_WRITER method add line by line id,name,surname,country,writing_type information to Writer obj
    public static void add_writer(String id,String name,String surname,String country,String  writing_type,ArrayList<Writer>writers)
    {   writers.add(new Writer(id,name,surname,country, writing_type));   }
    
    //READ FILM FILE 
    public static boolean readFilm(String lines,ArrayList<FeatureFilm>feature_films,	ArrayList<ShortFilm>short_film,
    						ArrayList<Documentary>documentarys,    ArrayList<TVSeries>TV_series,ArrayList<Actor>Actors,	ArrayList<Director>Directors,  
    						ArrayList<ChildActor>ChildActors,   ArrayList<StuntPerformer>stuntPerformers,    ArrayList<Writer>writers)
    {
    	String [] FilmArr=lines.split("\t");
    	if(FilmArr[0].equals("FeatureFilm:")||FilmArr[0].equals("ShortFilm:")||FilmArr[0].equals("TVSeries:")||FilmArr[0].equals("Documentary:"))
    	{
    		String directorIdList=FilmArr[4];
    		String[] ArrDirectorIDs=directorIdList.split(",");
    		ArrayList<String> director_Ids=new ArrayList<String>();
    		for(String arr:ArrDirectorIDs)
    		{	if(find_directors(arr,Directors)==null){   return false;  	}
    			director_Ids.add(arr);}	
    		
    		String performedIdList=FilmArr[7];
    		String[] ArrPerformedIDs=performedIdList.split(",");
        	ArrayList<String> performed_Ids=new ArrayList<String>();
        	for(String arr:ArrPerformedIDs) {
        		if(find_performed(arr,Actors,ChildActors,stuntPerformers)==null)	{return false;}
        		performed_Ids.add(arr);		 } 
        	
        	if(FilmArr[0].equals("FeatureFilm:")||FilmArr[0].equals("ShortFilm:")||FilmArr[0].equals("TVSeries:"))
        	{
        		String genres=FilmArr[8];
        		String[] ArrGenres=genres.split(",");
        		ArrayList<String> genre=new ArrayList<String>();
        		for(String arr:ArrGenres)
        			genre.add(arr);
        		if(FilmArr[0].equals("FeatureFilm:"))
        		{
        			String writerIdList=FilmArr[10];
            		String[] ArrWriterIDs=writerIdList.split(",");
                	ArrayList<String> writer_Ids=new ArrayList<String>();
                	for(String arr:ArrWriterIDs) {
                		if( find_writer(arr,writers)==null) { return false;}
                		writer_Ids.add(arr);		 }
                	add_feature_film(FilmArr[1],FilmArr[2],FilmArr[3],director_Ids,FilmArr[5],FilmArr[6],performed_Ids,
            				genre,FilmArr[9],writer_Ids,FilmArr[11],feature_films);
        		}
            	if(FilmArr[0].equals("ShortFilm:"))
        			add_short_film(FilmArr[1],FilmArr[2],FilmArr[3],director_Ids,FilmArr[5],FilmArr[6],performed_Ids,
            				genre,FilmArr[9],FilmArr[10],short_film);
            	if(FilmArr[0].equals("TVSeries:"))
        			add_TV_series(FilmArr[1],FilmArr[2],FilmArr[3],director_Ids,FilmArr[5],FilmArr[6],performed_Ids,
            				genre,FilmArr[9],FilmArr[10],FilmArr[11],FilmArr[12],FilmArr[13],TV_series);
        	}
        	else 
        	{       if(FilmArr[0].equals("Documentary:"))
    				add_documentary(FilmArr[1],FilmArr[2],FilmArr[3],director_Ids,FilmArr[5],FilmArr[6],performed_Ids,FilmArr[8],documentarys);        	} 		
    	}	
    	else   System.out.println("This people.txt command is not defined");
    	return true;    	
    	}	  	  	
    

  //ADD_FEATURE_FILM method add line by line id, title, language, directorIDs, length,	country, performedIDs, genres, releaseDate,
    // writerIDs, budget information to Feature Film obj
    public static void add_feature_film(String id,	String title, String language,ArrayList<String> directorIdList, String length,
			String country,ArrayList<String> performedIdList,ArrayList<String> genres,	 String releaseDate,
			ArrayList<String> writerIdList,String budget,ArrayList<FeatureFilm>feature_films)
    {       	
    	feature_films.add(new FeatureFilm(id, title, language, directorIdList, length, country, 
    			performedIdList, genres, releaseDate,  writerIdList, budget));
    }
    
  //ADD_SHORT_FILM method add line by line id, title, language, directorIDs, length,	country, performedIDs, genres, releaseDate,
    // writerIDs, budget information to Short Film obj
    public static void add_short_film(String id,	String title, String language,ArrayList<String> directorIdList, String length,
			String country,ArrayList<String> performedIdList,ArrayList<String> genres,	 String releaseDate,
			String writerIdList,ArrayList<ShortFilm>short_films)
    {       	
    	String[] ArrWriterIDs=writerIdList.split(",");
    	ArrayList<String> writer_Ids=new ArrayList<String>();
    	for(String arr:ArrWriterIDs)
    		writer_Ids.add(arr);
    	int control_length=Integer.parseInt(length);
    	if(control_length<=40&&control_length>0)
    		short_films.add(new ShortFilm(id, title, language, directorIdList, length, country, 
    			performedIdList, genres, releaseDate,  writer_Ids));
    }
    
    //ADD_TV_SERIES method add line by line id, title, language, directorIDs, length,	country, performedIDs, genres, 
    // writerIDs,startDate, endDate, season, episodes,  information to TVSeries obj
    public static void add_TV_series(String id,	String title, String language,ArrayList<String> directorIdList, String length,
			String country,ArrayList<String> performedIdList,ArrayList<String> genres,
			String writerIdList,String startDate,String endDate,String season,String episodes, ArrayList<TVSeries>TV_series )
    {   
    	
    	String[] ArrWriterIDs=writerIdList.split(",");
    	ArrayList<String> writer_Ids=new ArrayList<String>();
    	for(String arr:ArrWriterIDs)
    		writer_Ids.add(arr);
    	
    	TV_series.add(new TVSeries(id, title, language, directorIdList, length, country, 
    			performedIdList, genres, writer_Ids, startDate, endDate, season, episodes));
    }

  //ADD_DOCUMENTARY method add line by line id, title, language, directorIDs, length,	country, performedIDs, releaseDate,
    // information to Documentary obj
    public static void add_documentary(String id,	String title, String language,ArrayList<String> directorIdList, String length,
			String country,ArrayList<String> performedIdList, String releaseDate, ArrayList<Documentary>documentarys)
    {   
    	documentarys.add(new Documentary(id, title, language, directorIdList, length, country, 
    			performedIdList, releaseDate));
    }
    
    
    public static void readCommands(String [] lines,ArrayList<Rate>rates,ArrayList<User>Users, ArrayList<Actor>Actors ,	ArrayList<Director>Directors,    
    		ArrayList<ChildActor>ChildActors,	ArrayList<StuntPerformer>stuntPerformers,    ArrayList<Writer>writers,
    		ArrayList<FeatureFilm>feature_films,	ArrayList<ShortFilm>shortFilms,	ArrayList<Documentary>documentarys, 
    		ArrayList<TVSeries>TV_series)
    {
    	
    	try {
            File file = new File("output.txt");
            if (file.createNewFile()) {
              //System.out.println("File created: " + file.getName());
            } else {
              //System.out.println("File already exists.");
            }
            FileWriter writer_file = new FileWriter(file);
       for(String line:lines)
       {
        	 String [] CommandArr=line.split("\t");
         //ADD FEATUREFILM
    	if(CommandArr[0].equals("ADD")&&CommandArr[1].equals("FEATUREFILM"))
    	{
    		boolean already_have=false;
    		String command="FeatureFilm:";
    		writer_file.write(CommandArr[0]+"\t"+CommandArr[1]+"\t"+CommandArr[2]+"\t"+CommandArr[3]+"\t"+CommandArr[4]+"\t"+
    				CommandArr[5]+"\t"+CommandArr[6]+"\t"+CommandArr[7]+"\t"+CommandArr[8]+"\t"+CommandArr[9]+"\t"+CommandArr[10]+
    				"\t"+CommandArr[11]+"\t"+CommandArr[12]+"\n \n");
    		for(FeatureFilm f:feature_films)
    		{	if(f.getId().equals(CommandArr[2]))
    				already_have=true;
    		}
    		if(already_have)
    		{   	writer_file.write("Command Failed \n" + "Film ID: "+CommandArr[2] +" \n"+ "Film title: "+CommandArr[3]+"\n");    		}
    		else
    		{			
    			command=command+"\t"+CommandArr[2]+"\t"+CommandArr[3]+"\t"+CommandArr[4]+"\t"+
    				CommandArr[5]+"\t"+CommandArr[6]+"\t"+CommandArr[7]+"\t"+CommandArr[8]+"\t"+CommandArr[9]+"\t"+CommandArr[10]+
    				"\t"+CommandArr[11]+"\t"+CommandArr[12];
    			boolean true_elements=readFilm(command,feature_films,	shortFilms,	documentarys, TV_series,Actors,Directors, 
    					ChildActors,stuntPerformers,  writers);
    			if(true_elements)
    				writer_file.write("Feature Film added successfully"+"\n"+"Film ID: "+CommandArr[2]+"\n"+"Film title: "+CommandArr[3]+"\n");
    			else {   	writer_file.write("Command Failed \n" + "Film ID: "+CommandArr[2] +"\n"+ "Film title: "+CommandArr[3]+"\n");    		}
    		}		
    	}
    	//LIST FEATUREFILMS
    	if(CommandArr[0].equals("LIST")&&CommandArr[1].equals("FEATUREFILMS"))
    	{
    		if(CommandArr[2].equals("BEFORE"))
    		{
    			writer_file.write(CommandArr[0]+"\t"+CommandArr[1]+"\t"+CommandArr[2]+"\t"+CommandArr[3]+" \n \n");
    			ArrayList<String> before=new ArrayList<String>();
    			int year=Integer.parseInt(CommandArr[3]);
    			for(FeatureFilm f:feature_films)
    			{	String[] y=f.getReleaseDate().split("\\.");
    				if(year>Integer.parseInt(y[2]))
    				{
    					String info="Film title: "+f.getTitle()+"("+y[2]+")"+"\n"+f.getLength()+" min\n"+"Language: "+f.getLanguage()+"\n";
    					before.add(info);
    				}	
    			}
    			if(before.size()==0)
    				writer_file.write("No result \n");
    			for(String b:before)
    				writer_file.write(b+"\n");
    		}
    		if(CommandArr[2].equals("AFTER"))
    		{
    			writer_file.write(CommandArr[0]+"\t"+CommandArr[1]+"\t"+CommandArr[2]+"\t"+CommandArr[3]+"\n \n");
    			ArrayList<String> after=new ArrayList<String>();
    			int year=Integer.parseInt(CommandArr[3]);
    			for(FeatureFilm f:feature_films)
    			{	String[] y=f.getReleaseDate().split("\\.");
    				if(year<=Integer.parseInt(y[2]))
    				{
    					String info="Film title: "+f.getTitle()+"("+y[2]+") "+"\n"+f.getLength()+" min\n"+"Language: "+f.getLanguage()+"\n";
    					after.add(info);
    				}	
    			}
    			if(after.size()==0)
    				writer_file.write("No result \n");
    			for(String b:after)
    				writer_file.write(b+"\n");
    		}
    	}
    	//RATE 
    	if(CommandArr[0].equals("RATE"))
    	{
    		writer_file.write(CommandArr[0]+"\t"+CommandArr[1]+"\t"+CommandArr[2]+"\t"+CommandArr[3]+"\n \n");
    		String command="rate";
    		boolean control_rate=true;
    		boolean control_user=false;
    		boolean control_film=false;
    		int rate=Integer.parseInt(CommandArr[3]);
    		boolean rate_point=false;
    		if(rate<11 && rate>=0)
    			rate_point=true;
    		for(User u:Users)
    		{	if(u.getUnique_id().equals(CommandArr[1])&&(!control_user))
				{	control_user=true;}
    		}
    		for(Rate r:rates)
    		{
    			if(control_rate&&(r.getUserId()).equals(CommandArr[1]))
    			{	for(String s:r.filmId)
    				{	if(s.equals(CommandArr[2]))
    					{
    						control_rate=false;
    						writer_file.write("This film was earlier rated \n");
    						break;}    				}
    			}
    		}
    		if(control_rate&&control_user&&rate_point)
    		{	
    			String film=null;					
    			film=find_film(command,CommandArr[2],feature_films, shortFilms, documentarys, TV_series, Actors, Directors,    
    					    		ChildActors, stuntPerformers, writers);
    			if(film!=null)
    			{   String[] film_info=film.split("\t");
    				writer_file.write("Film rated successfully \n");
    				writer_file.write("Film type: "+film_info[0]+"\n"+"Film title: "+film_info[1]+"\n");
    				add_rate(CommandArr[1],CommandArr[2],CommandArr[3],rates);
    				control_film=true;  		}
    		}
    		else
    		{	if((!control_user)||(!control_film)||(!rate_point))
    				if(control_rate)
    					writer_file.write("Command Failed"+"\n"+"User ID: "+CommandArr[1]+"\n" +"Film ID: "+CommandArr[2]+"\n");
    		}
    		if(!rate_point)
    			System.out.println("Rate point must be <=10 and >=0");
    	}
    	//REMOVE RATE OR EDIT RATE
    	if(CommandArr[0].equals("REMOVE")||CommandArr[0].equals("EDIT"))
    	{
    		String command="remove";
    		if(CommandArr[0].equals("REMOVE"))
    			writer_file.write(CommandArr[0]+"\t"+CommandArr[1]+"\t"+CommandArr[2]+"\t"+CommandArr[3]+"\n \n");
    		if(CommandArr[0].equals("EDIT"))
    			writer_file.write(CommandArr[0]+"\t"+CommandArr[1]+"\t"+CommandArr[2]+"\t"+CommandArr[3]+"\t"+CommandArr[4]+"\n \n");
    		boolean control_rate=true;
    		boolean control_user=false;
    		for(User u:Users)
    		{	if(u.getUnique_id().equals(CommandArr[2])&&(!control_user))
				{	control_user=true;}
    		}
    		for(Rate r:rates)
    		{
    			if(control_rate&&((r.getUserId()).equals(CommandArr[2])))
    			{	int size_Arr=(r.filmId).size();
    				for(int i=0;i<size_Arr;i++)
    			    {	
    					if(r.filmId.get(i).equals(CommandArr[3]))
    					{	
    						String film_title=find_film(command,CommandArr[3],feature_films, shortFilms, documentarys, TV_series, Actors, Directors,    
    					    		ChildActors, stuntPerformers, writers);
    						control_rate=false;
    						if(CommandArr[0].equals("REMOVE"))
    						{	writer_file.write("Your film rating was removed successfully \n");
    							r.filmId.remove(i);
    							r.ratingPoint.remove(i);
    							writer_file.write("Film title: "+film_title+"\n");	}					   						
    						if(CommandArr[0].equals("EDIT"))
    						{	writer_file.write("New ratings done successfully"+"\n");
    							r.ratingPoint.set(i, CommandArr[4]);
    							writer_file.write("Film title: "+film_title+" \n Your rating: "+CommandArr[4]+"\n");
    						}    						
    						break;  	}
    				}
    			}
    		}	
    		if(control_rate||(!control_user))
    			writer_file.write("Command Failed"+"\n"+"User ID: "+CommandArr[2]+"\n"
    							+ "Film ID: "+CommandArr[3]+"\n");    			
    	}
    	//VIEWFILM
    	if(CommandArr[0].equals("VIEWFILM")) 
    	{
    		writer_file.write(CommandArr[0]+"\t"+CommandArr[1]+"\n \n");
    		String command="view";
    		String film_info=find_film(command,CommandArr[1],feature_films, shortFilms, documentarys, TV_series, Actors, Directors,    
		    		ChildActors, stuntPerformers, writers);
    		String rate=find_rate(CommandArr[1],rates);
    		writer_file.write(film_info+rate+"\n");    		   		
    	}
    	
    	if(CommandArr[0].equals("LIST")&&CommandArr[1].equals("FILMS")&&CommandArr[2].equals("BY")&&CommandArr[3].equals("COUNTRY"))
    	{
    		writer_file.write(CommandArr[0]+"\t"+CommandArr[1]+"\t"+CommandArr[2]+"\t"+CommandArr[3]+"\t"+CommandArr[4]+"\n \n");	
    		ArrayList<String> list=new ArrayList<String>();
    		for(ShortFilm s:shortFilms)
    		{   if(s.getCountry().equals(CommandArr[4]))
    			{
    				String info1="Film title: "+s.getTitle()+"\n"+s.getLength()+" min\n"+"Language: "+s.getLanguage()+"\n ";
    				list.add(info1);    			}    		}
    		for(TVSeries t:TV_series)
    		{   if(t.getCountry().equals(CommandArr[4]))
    			{
    				String info1="Film title: "+t.getTitle()+"\n"+t.getLength()+" min\n"+"Language: "+t.getLanguage()+"\n ";
    				list.add(info1);    			}    		}
    		for(FeatureFilm f:feature_films)
    		{    if(f.getCountry().equals(CommandArr[4]))
    			{
    				String info1="Film title: "+f.getTitle()+"\n"+f.getLength()+" min\n"+"Language: "+f.getLanguage()+"\n ";
    				list.add(info1);    			}    		}	
    		for( Documentary d: documentarys )
    		{    if(d.getCountry().equals(CommandArr[4]))
    			{
    				String info1="Film title: "+d.getTitle()+"\n"+d.getLength()+" min\n"+"Language: "+d.getLanguage()+"\n ";
    				list.add(info1);    			}    		}
    		
    		if(list.size()==0)
    			writer_file.write("No result \n");
    		else{	
    			for(String a:list)
    				writer_file.write(a+"\n");    		}
    	}
    	
    	//LIST ARTISTS FROM ----
    	if(CommandArr[0].equals("LIST")&&CommandArr[1].equals("ARTISTS")&&CommandArr[2].equals("FROM"))
    	{
    		writer_file.write(CommandArr[0]+"\t"+CommandArr[1]+"\t"+CommandArr[2]+"\t"+CommandArr[3]+"\n \n");
    		//String info;	
    		ArrayList<String> list=new ArrayList<String>();
    		boolean director=true;	
    		boolean writer=true;
    		boolean actor=true;	
    		boolean child_actor=true;
    		boolean stunt_performer=true;
			list.add("Directors: ");
    		for(Director d:Directors)
    		{   if(d.getCountry().equals(CommandArr[3]))
    			{	director=false;
    				String info1=d.getName()+" "+d.getSurname()+" "+d.getAgent();
    				list.add(info1);    			}    		}
    		if(director)
    			list.add("No result ");
    		list.add(" ");
    		list.add("Writers:");
    		for(Writer w:writers)
    		{   if(w.getCountry().equals(CommandArr[3]))
    			{	writer=false;
    				String info1=w.getName()+" "+w.getSurname()+" "+w.getWriting_style();
    				list.add(info1);    			}    		}
    		if(writer)
    			list.add("No result ");
    		list.add(" ");
    		list.add("Actors:");
    		for(Actor aa:Actors)
    		{    if(aa.getCountry().equals(CommandArr[3]))
    			{	actor=false;
    				String info1=aa.getName()+" "+aa.getSurname()+" "+aa.getHeight()+" cm ";
    				list.add(info1);    			}    		}
    		if(actor)
    			list.add("No result ");
    		list.add(" ");
    		list.add("ChildActors: ");
    		for(ChildActor c:ChildActors)
    		{    if(c.getCountry().equals(CommandArr[3]))
    			{	child_actor=false;
    				String info1=c.getName()+" "+c.getSurname()+" "+c.getAge();
    				list.add(info1);    			}    		}
    		if(child_actor)
    			list.add("No result ");
    		list.add(" ");
    		list.add("StuntPerformers: ");
    		for( StuntPerformer s_p:stuntPerformers )
    		{    if(s_p.getCountry().equals(CommandArr[3]))
    			{	stunt_performer=false;
    				String info1=s_p.getName()+" "+s_p.getSurname()+" "+s_p.getHeight()+" cm ";
    				list.add(info1);    			}    		}
    		if(stunt_performer)
    			list.add("No result ");	
    	for(String a:list)
    		writer_file.write(a+"\n");
    		
    	}
    	//LIST FILM SERIES
    	if(CommandArr[0].equals("LIST")&&CommandArr[1].equals("FILM")&&CommandArr[2].equals("SERIES"))
    	{
    		writer_file.write(CommandArr[0]+"\t"+CommandArr[1]+"\t"+CommandArr[2]+"\n \n");
    		for(TVSeries s:TV_series)
    		{
    			String[] yearStart=s.getStartDate().split("\\.");
    			String[] yearEnd=s.getEndDate().split("\\.");
    			String start_date=yearStart[2];
    			String end_date=yearEnd[2];
    			writer_file.write(s.getTitle() + " ("+start_date+"-"+end_date+") \n"+s.getSeasons()+" season and "+s.getEpisodes()+" episodes \n\n");
    		}		
    	}
    	//LIST FILMS BY RATE DEGREE
    	if(CommandArr[0].equals("LIST")&&CommandArr[1].equals("FILMS")&&CommandArr[4].equals("DEGREE"))
    	{
    		writer_file.write(CommandArr[0]+"\t"+CommandArr[1]+"\t"+CommandArr[2]+"\t"+CommandArr[3]+"\t"+CommandArr[4]+"\n\n");
    		ArrayList<String> feature=new ArrayList<String>();
    		ArrayList<String>short_film=new ArrayList<String>();
    		ArrayList<String>document=new ArrayList<String>();
    		ArrayList<String>series=new ArrayList<String>();
    		String film_info;
    		int size_f=99;
    		String name;
    		String rate;
    		String rate1;
    		writer_file.write("FeatureFilm: \n");
    		for(FeatureFilm f:feature_films)
    		{	name=find_film("ratedegree",f.getId(),feature_films, shortFilms, documentarys, TV_series, Actors, Directors,    
    		    		ChildActors, stuntPerformers, writers);
    			if(find_rate(f.getId(),rates).equals("Awaiting for votes"))
    			{	rate="0/10 from 0 users";
    				rate1="0";    			}
    			else{
    				rate=find_rate(f.getId(),rates);
    				String[]value=find_rate(f.getId(),rates).split("/");
    			  	rate1=value[0];    	}  
    			film_info=rate1+size_f+"\n"+name+"	Ratings:"+rate;
    			feature.add(film_info);  
    			size_f--;
    		}	
    		if(feature.size()!=0)
    		{	 Collections.sort(feature);
    			Collections.reverse(feature);
    			for(String ff:feature)
    			{	String[] rating_f=ff.split("\n");
    				writer_file.write(rating_f[1]+"\n");}    			
    			}
    		writer_file.write("\nShortFilm: \n");
    		for(ShortFilm s:shortFilms)
    		{	name=find_film("ratedegree",s.getId(),feature_films, shortFilms, documentarys, TV_series, Actors, Directors,    
    		    		ChildActors, stuntPerformers, writers);
    			if(find_rate(s.getId(),rates).equals("Awaiting for votes"))
    			{	rate="0/10 from 0 users";
    				rate1="0";    			}
    			else{
    				rate=find_rate(s.getId(),rates);
    				String[]value=find_rate(s.getId(),rates).split("/");
    			  	rate1=value[0];    			}  
    			film_info=rate1+size_f+"\n"+name+"	Ratings:"+rate;
    			short_film.add(film_info);
    			size_f--;
    		}	
    		if(short_film.size()!=0)
    		{	 Collections.sort(short_film);
    			 Collections.reverse(short_film);
    			for(String ss:short_film)
    			{	String[] rating_s=ss.split("\n");
    			writer_file.write(rating_s[1]+"\n");}
    			}
    		writer_file.write("\nDocumentary: \n");
    		for(Documentary d:documentarys)
    		{	name=find_film("ratedegree",d.getId(),feature_films, shortFilms, documentarys, TV_series, Actors, Directors,    
    		    		ChildActors, stuntPerformers, writers);
    			if(find_rate(d.getId(),rates).equals("Awaiting for votes"))
    			{	rate="0/10 from 0 users";
    				rate1="0";    			}
    			else{
    				rate=find_rate(d.getId(),rates);
    				String[]value=find_rate(d.getId(),rates).split("/");
    			  	rate1=value[0];    			}  
    			film_info=rate1+size_f+"\n"+name+"	Ratings:"+rate;
    			document.add(film_info); 
    			size_f--;
    		}	
    		if(document.size()!=0)
    		{	 Collections.sort(document);
    			 Collections.reverse(document);
    			for(String dd:document)
    			{	String[] rating_d=dd.split("\n");
    			writer_file.write(rating_d[1]+"\n");}
    			}
    		writer_file.write("\nTVSeries: \n");
    		for(TVSeries t:TV_series )
    		{	name=find_film("ratedegree",t.getId(),feature_films, shortFilms, documentarys, TV_series, Actors, Directors,    
    		    		ChildActors, stuntPerformers, writers);
    			if(find_rate(t.getId(),rates).equals("Awaiting for votes"))
    			{	rate="0/10 from 0 users";
    				rate1="0";    			}
    			else{
    				rate=find_rate(t.getId(),rates);
    				String[]value=find_rate(t.getId(),rates).split("/");
    			  	rate1=value[0];    			}  
    			film_info=rate1+size_f+"\n"+name+"	Ratings:"+rate;
    			series.add(film_info); 
    			size_f--;
    		}	
    		if(document.size()!=0)
    		{	 Collections.sort(series);
    			 Collections.reverse(series);
    			for(String tt:series)
    			{	String[] rating_d=tt.split("\n");
    			writer_file.write(rating_d[1]+"\n");}
    			}	
    	}
    	//LIST USER RATES 
    	if(CommandArr[0].equals("LIST")&&CommandArr[1].equals("USER")&&CommandArr[3].equals("RATES"))
    	{
    		writer_file.write(CommandArr[0]+"\t"+CommandArr[1]+"\t"+CommandArr[2]+"\t"+CommandArr[3]+"\n \n");
    		boolean control_user_found=true;
    		String command="listUserRates";
    		
    		for(User u:Users)
    		{	if(u.getUnique_id().equals(CommandArr[2])&&control_user_found)
    			{
    			  	control_user_found=false;}
    		}
    		
    		if(control_user_found)
    			writer_file.write("Command Failed \n"
    					+ "User ID: "+CommandArr[2]+"\n");
    		else {
    			for(Rate r:rates)
    			{	
    				if(r.getUserId().equals(CommandArr[2]))
    				{
    					int size_arraylist_filmId=r.filmId.size();
    					for(int i=0;i<size_arraylist_filmId;i++)
    					{	writer_file.write(find_film(command,r.filmId.get(i),feature_films, shortFilms, documentarys, TV_series, Actors, Directors,    
    							ChildActors, stuntPerformers, writers)+":"+r.ratingPoint.get(i)+"\n");							}
    				}    					
    			}
    		}
    	}	
    	writer_file.write(" \n -----------------------------------------------------------------------------------------------------\n");}
    	writer_file.close();
    	}catch (IOException e) {
             System.out.println("An error occurred.");
             e.printStackTrace();
}	  }
    
    //FIND_FILM
    public static String find_film(String command,String filmId,ArrayList<FeatureFilm>feature_films,	ArrayList<ShortFilm>shortFilms,	ArrayList<Documentary>documentarys, 
    		ArrayList<TVSeries>TV_series,ArrayList<Actor>Actors,	ArrayList<Director>Directors,    
    		ArrayList<ChildActor>ChildActors,	ArrayList<StuntPerformer>stuntPerformers,    ArrayList<Writer>writers)
    {
    	boolean control_type_of_film=true;
		String type_film=null;
		String film_info=null;
		String find_film_out=null;
		
		if(control_type_of_film)
		{	film_info=find_feature_film( filmId, feature_films, Actors, Directors, ChildActors, stuntPerformers, writers);
			if(film_info!=null)
			{	control_type_of_film=false;
				type_film="FeatureFilm";}			
			else {
				film_info=find_short_film( filmId, shortFilms, Actors, Directors, ChildActors, stuntPerformers, writers);
				if(film_info!=null)
				{	control_type_of_film=false;
					type_film="ShortFilm";}	
				else {
					film_info=find_documentarys( filmId, documentarys, Actors, Directors, ChildActors, stuntPerformers, writers);
					if(film_info!=null)
					{	control_type_of_film=false;
						type_film="Documentary";}			
					else {
						film_info=find_TV_series( filmId, TV_series, Actors, Directors, ChildActors, stuntPerformers, writers);
						if(film_info!=null)
						{	control_type_of_film=false;
							type_film="TVSeries";}
						else {
							if(control_type_of_film)
								find_film_out="Command Failed";	
							}	}	}	}	}
		
		if(!control_type_of_film)
		{
			if(command.equals("listUserRates")||command.equals("remove"))
			{
				String[] filmArr=film_info.split("\n");
				String[] filmTitle=filmArr[0].split(" ");
				find_film_out=filmTitle[0];			}	
			
			if(command.equals("ratedegree"))
			{
				String[] filmArr=film_info.split("\n");
				find_film_out=filmArr[0];	}
	
			if(command.equals("rate"))
			{
				String[] filmArr=film_info.split("\n");
				String[] filmTitle=filmArr[0].split(" ");
				find_film_out=type_film+"\t"+filmTitle[0];			}
			
			if(command.equals("view"))
			{
				String[] filmArr=film_info.split("\n");
				if(type_film.equals("TVSeries"))
					find_film_out=filmArr[0]+"\n"+filmArr[1]+"\n"+filmArr[2]+"\n"+filmArr[3]+"\n"+filmArr[4]+"\n"+filmArr[5]+"\n";
				if(type_film.equals("FeatureFilm")||type_film.equals("ShortFilm"))
					find_film_out=filmArr[0]+"\n"+filmArr[1]+"\n"+filmArr[2]+"\n"+filmArr[3]+"\n"+filmArr[4]+"\n";
				if(type_film.equals("Documentary"))
					find_film_out=filmArr[0]+"\n"+filmArr[1]+"\n"+filmArr[2]+"\n";
			}	
		}
		return find_film_out;
	}
    
    //FIND_FEATURE_FILM method to find featur_film with finding_film. Find the film information documentary	,film_title,film_year,language etc
    public static String find_feature_film(String finding_film,ArrayList<FeatureFilm>feature_films,	ArrayList<Actor>Actors,	ArrayList<Director>Directors,    
    		ArrayList<ChildActor>ChildActors,	ArrayList<StuntPerformer>stuntPerformers,    ArrayList<Writer>writers)
    {
    	boolean control_type_of_film=true;
    	String feature_film=null;
    	String film_title=null;    	
    	String film_year=null;
    	String language=null;
    	String length=null;
    	String country=null; 
    	StringBuffer d_info = new StringBuffer();
    	StringBuffer p_info = new StringBuffer();
    	StringBuffer g_info = new StringBuffer();
    	StringBuffer w_info = new StringBuffer();
    	for(FeatureFilm f:feature_films)
    	{	
    		if(f.getId().equals(finding_film)&&control_type_of_film)
			{	String[] year=(f.getReleaseDate()).split("\\.");
				film_title=f.getTitle();
				film_year =year[2];				
				language=f.getLanguage();
		    	length=f.getLength();
		    	country=f.getCountry();
    			control_type_of_film=false;
    			for(int i=0;i<f.genres.size();i++)
    			{   g_info.append(f.genres.get(i));
    				if((i+1)!=f.genres.size())
    					g_info.append(", "); }
    			for(int i=0;i<f.directorIDs.size();i++)
				{   d_info.append(find_directors(f.directorIDs.get(i),Directors));
					if((i+1)!=f.directorIDs.size())
						d_info.append(", ");				}
    			for(int i=0;i<f.performedIDs.size();i++)
				{	p_info.append(find_performed(f.performedIDs.get(i),Actors,ChildActors,stuntPerformers));
					if((i+1)!=f.performedIDs.size())
						p_info.append(", ");				}
    			for(int i=0;i<f.writerIDs.size();i++)
				{	w_info.append(find_writer(f.writerIDs.get(i),writers));
					if((i+1)!=f.writerIDs.size())
						w_info.append(", ");				}
			}			
    	}
    	String genre_information =g_info.toString();
		String director_information = d_info.toString();
		String performed_information = p_info.toString();
		String writer_information = w_info.toString();
    	if(!control_type_of_film)
    		feature_film=film_title+" ("+ film_year+")"+"\n"+ genre_information+"\n"+"Writers: "+writer_information + "\n"+ "Directors: "
    				+ director_information+ "\n"+"Stars: "+ performed_information + "\n"+country+ "\n"+language + "\n"+ length 	 ;
    	return feature_film;
    }
    
    //FIND_SORHT_FILM method to find short_film with finding_film. Find the film information documentary,film_title,film_year,language etc
    public static String find_short_film(String finding_film, ArrayList<ShortFilm>shortFilms , ArrayList<Actor>Actors, ArrayList<Director>Directors,    
			ArrayList<ChildActor>ChildActors, ArrayList<StuntPerformer>stuntPerformers, ArrayList<Writer>writers)
    {
    	boolean control_type_of_film=true;
    	String short_film=null;
    	String film_title=null;    	
    	String film_year=null;
    	String language=null;
    	String length=null;
    	String country=null;  
    	StringBuffer d_info = new StringBuffer();
    	StringBuffer p_info = new StringBuffer();
    	StringBuffer g_info = new StringBuffer();
    	StringBuffer w_info = new StringBuffer();
    	for(ShortFilm s:shortFilms)
    	{	
    		if(s.getId().equals(finding_film)&&control_type_of_film)
    		{	String[] year=s.getReleaseDate().split("\\.");
    			film_title=s.getTitle();
    			film_year=year[2];				
    			language=s.getLanguage();
    			length=s.getLength();
    			country=s.getCountry();
    			control_type_of_film=false;
    			for(int i=0;i<s.genres.size();i++)
    			{   g_info.append(s.genres.get(i));
    				if((i+1)!=s.genres.size())
    					g_info.append(" , "); }
    			for(int i=0;i<s.directorIDs.size();i++)
    			{	d_info.append(find_directors(s.directorIDs.get(i),Directors));
					if((i+1)!=s.directorIDs.size())
						d_info.append(" , ");				}
    			for(int i=0;i<s.performedIDs.size();i++)
    			{	p_info.append(find_performed(s.performedIDs.get(i),Actors,ChildActors,stuntPerformers));
					if((i+1)!=s.performedIDs.size())
						p_info.append(" , ");				}
    			for(int i=0;i<s.writerIDs.size();i++)
    			{	w_info.append(find_writer(s.writerIDs.get(i),writers));
    				if((i+1)!=s.writerIDs.size())
    					w_info.append(" , ");				}
    		}
    	}
    	String genre_information =g_info.toString();
    	String director_information = d_info.toString();
		String performed_information = p_info.toString();
		String writer_information = w_info.toString();
		if(!control_type_of_film)
    		short_film=film_title+" ("+ film_year+")"+"\n"+ genre_information+"\n"+"Writers: "+writer_information +  "\n"+"Directors: "
    				+ director_information+ "\n"+"Stars: "+ performed_information + "\n"+country+ "\n"+language + "\n"+ length 	 ;
    	return short_film;
    }
    
    //FIND_DOCUMENTARYS method to find documentary with finding_film. Find the film information documentary	,film_title,film_year,language etc
    public static String find_documentarys(String finding_film, ArrayList<Documentary>documentarys , ArrayList<Actor>Actors, ArrayList<Director>Directors,    
    								ArrayList<ChildActor>ChildActors, ArrayList<StuntPerformer>stuntPerformers, ArrayList<Writer>writers)
    {
    	boolean control_type_of_film=true;
    	String documentary=null;
    	String film_title=null;    	
    	String film_year=null;
    	String language=null;
    	String length=null;
    	String country=null;    	
    	StringBuffer d_info = new StringBuffer();
    	StringBuffer p_info = new StringBuffer();
    	for(Documentary d:documentarys)
    	{	
    		if(d.getId().equals(finding_film)&&control_type_of_film)
			{	String[] year=d.getReleaseDate().split("\\.");
				film_title=d.getTitle();
				film_year=year[2];				
				language=d.getLanguage();
		    	length=d.getLength();
		    	country=d.getCountry();
    			control_type_of_film=false;
    			
    			for(int i=0;i<d.directorIDs.size();i++)
    			{	d_info.append(find_directors(d.directorIDs.get(i),Directors));
    				if((i+1)!=d.directorIDs.size())
    					d_info.append(", ");				}
    			for(int i=0;i<d.performedIDs.size();i++)
    			{	p_info.append(find_performed(d.performedIDs.get(i),Actors,ChildActors,stuntPerformers));
    				if((i+1)!=d.performedIDs.size())
    					p_info.append(", ");				}	
			}	
    	}
    	String director_information = d_info.toString();
    	String performed_information = p_info.toString();
		if(!control_type_of_film)
    		documentary=film_title+" ("+ film_year+")"+"\n"+"Directors: "+director_information+"\n"+"Stars: "+ performed_information +"\n"+
    				country+"\n" +language+"\n"+ length  ;;
    	return documentary;
    	
    }
    
  //FIND_TV_SERIES method to find TV_series with finding_film. Find the film information documentary,film_title,film_year,language etc
    public static String find_TV_series(String finding_film, ArrayList<TVSeries>TV_Series , ArrayList<Actor>Actors, ArrayList<Director>Directors,    
			ArrayList<ChildActor>ChildActors, ArrayList<StuntPerformer>stuntPerformers, ArrayList<Writer>writers)
    {
    	boolean control_type_of_film=true;
    	String TV_series=null;
    	String film_title=null;    	
    	String start_date=null;
    	String end_date=null;
    	String language=null;
    	String length=null;
    	String country=null;
    	String season=null;
    	String episodes=null;
    	StringBuffer d_info = new StringBuffer();
    	StringBuffer p_info = new StringBuffer();
    	StringBuffer g_info = new StringBuffer();
    	StringBuffer w_info = new StringBuffer();
    	for(TVSeries t: TV_Series)
    	{	
    		if(t.getId().equals(finding_film)&&control_type_of_film)
    		{	String[] yearStart=t.getStartDate().split("\\.");
    			String[] yearEnd=t.getEndDate().split("\\.");
    			film_title=t.getTitle();
    			start_date=yearStart[2];
    			end_date=yearEnd[2];
    			language=t.getLanguage();
    			length=t.getLength();
    			country=t.getCountry();
    			season=t.getSeasons();
    			episodes=t.getEpisodes();
    			control_type_of_film=false;
    			for(int i=0;i<t.genres.size();i++)
    			{   g_info.append(t.genres.get(i));
    				if((i+1)!=t.genres.size())
    					g_info.append(", "); }
    			for(int i=0;i<t.directorIDs.size();i++)
    			{	
    				d_info.append(find_directors(t.directorIDs.get(i),Directors));
    				if((i+1)!=t.directorIDs.size())
    					d_info.append(", ");				}
    			for(int i=0;i<t.performedIDs.size();i++)
    			{	p_info.append(find_performed(t.performedIDs.get(i),Actors,ChildActors,stuntPerformers));
    				if((i+1)!=t.performedIDs.size())
    					p_info.append(", ");				}
    			for(int i=0;i<t.writerIDs.size();i++)
    			{	w_info.append(find_writer(t.writerIDs.get(i),writers));
    				if((i+1)!=t.writerIDs.size())
    					w_info.append(", ");				}
    		}
    	}
    	String genre_information =g_info.toString();
    	String director_information = d_info.toString();
		String performed_information = p_info.toString();
		String writer_information = w_info.toString();
		if(!control_type_of_film)
			TV_series=film_title+" ("+ start_date+"-"+end_date+")"+"\n"+season +" seasons "+ episodes+" episodes"+"\n"+
					genre_information +"\n"+"Writers: "+writer_information +"\n"+"Directors: "+director_information+
						"\n"+"Stars: "+ performed_information + "\n" +  country +"\n" +  language +"\n"+length  ;
    	return TV_series;
    }
    
    //FIND_WRITER method to find writer with writerId
    public static String find_writer(String writerId,ArrayList<Writer>writers)
    {
    	String writer_ids=null;
    	for(Writer w:writers)
    		if(w.getUnique_id().equals(writerId))
    			writer_ids=w.getName()+" "+w.getSurname();
    	return writer_ids;    }
    
    //FIND_DIRECTOR method to find director with directorId
    public static String find_directors(String directorId,ArrayList<Director>Directors)
    {
    	String director_ids = null;
    	for(Director d:Directors)
    		if(d.getUnique_id().equals(directorId))
    			director_ids=d.getName()+" "+d.getSurname();
    	return director_ids;    }
    
  //FIND_PERFORMED method to find performed with performedId
    public static String find_performed(String performed_Ids,ArrayList<Actor>Actors,ArrayList<ChildActor>ChildActors,	ArrayList<StuntPerformer>stuntPerformers)
    {
    	String performed_ids = null;
    	if(performed_ids==null)
    	{	for(Actor a:Actors)
    			if(a.getUnique_id().equals(performed_Ids))
					performed_ids =a.getName()+" "+a.getSurname();}
    	if(performed_ids==null)
    	{	for(ChildActor c:ChildActors)
    			if(c.getUnique_id().equals(performed_Ids))
					performed_ids =c.getName()+" "+c.getSurname();}
    	if(performed_ids==null)
    	{	for(StuntPerformer s:stuntPerformers)
    			if(s.getUnique_id().equals(performed_Ids))
					performed_ids =s.getName()+" "+s.getSurname();}
    	return performed_ids ;    }
    
	//ADD_USER method add line by line id,name,surname,country,height information to User obj
	public static void add_rate( String userId, String filmId, String ratingPoint,ArrayList<Rate>rates)
	{   
		boolean control_rate_user=true;
		for(Rate r:rates)
		{	if(r.getUserId().equals(userId)&&control_rate_user)
			{	(r.filmId).add(filmId);
				(r.ratingPoint).add(ratingPoint);
				control_rate_user=false;
				break;	}
		}	
		ArrayList<String> filmIds = new ArrayList<String>();
		filmIds.add(filmId);
		ArrayList <String> ratingPoints=new ArrayList<String>();
		ratingPoints.add(ratingPoint);
		if(control_rate_user)
			rates.add(new Rate(userId,filmIds,ratingPoints));
	}
	
	//FIND_RATE method to find rate with filmID
	public static String find_rate(String filmID,ArrayList<Rate>rates)
	{
		ArrayList<String> str=new ArrayList<String>();
		double rate=0;
		for(Rate r:rates)
		{	int size=r.filmId.size();
			for(int i=0;i<size;i++)
			{
				if(r.filmId.get(i).equals(filmID))
					str.add(r.ratingPoint.get(i));}					
		}
		String result;
		int str_size=str.size();
		for(String s:str)
			rate += Integer.parseInt(s);
		if(rate==0)
			return "Awaiting for votes";
		rate=rate/str_size;
		
		double rate_roll=(double) Math.round(rate* 10.0) / 10.0;
		String rate1=String.valueOf(rate_roll);
		rate1=rate1.substring(0,3);
		result=rate1+"/10 from "+String.valueOf(str_size)+" users";
					
		return result;
	}
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<User>users=new ArrayList<>();
		ArrayList<Actor>actors=new ArrayList<>();
        ArrayList<Director>directors=new ArrayList<>();
        ArrayList<ChildActor>childActors=new ArrayList<>();
        ArrayList<StuntPerformer>stuntPerformer=new ArrayList<>();
        ArrayList<Writer>writers=new ArrayList<>();
        
        //read people.txt
        String[] lines_people=readFile(args[0]);//read author.txt
        for(String line:lines_people)
        {    readPeople(line,users,actors, directors,childActors,stuntPerformer,writers);      	}
        
        ArrayList<FeatureFilm>feature_films=new ArrayList<>();
		ArrayList<ShortFilm>short_film=new ArrayList<>();
        ArrayList<Documentary>documentarys=new ArrayList<>();
        ArrayList<TVSeries>TV_series=new ArrayList<>();
        //read film.txt
        String[] lines_film=readFile(args[1]);//read film.txt
        for(String line:lines_film)
        {    boolean val=readFilm(line,feature_films,short_film, documentarys,TV_series,actors, directors,childActors,stuntPerformer,writers);  
        	if(val)   continue;
        }
        
        ArrayList<Rate>rates=new ArrayList<>();
        //read commands.txt
        String[] lines_command=readFile(args[2]);//read command.txt
        readCommands(lines_command,rates,users,actors, directors,childActors,stuntPerformer,writers,feature_films,short_film, documentarys,TV_series); 
	}
}
