package com.example.wordle;

public class NBA_Teams {
    NBATeam[] teams=new NBATeam[30];
    public NBA_Teams()
    {
        teams[0]=new NBATeam("Brooklyn","Nets",R.drawable.nets);
        teams[1]=new NBATeam("Boston","Celtics",R.drawable.celtics);
        teams[2]=new NBATeam("New York","Knicks",R.drawable.knicks);
        teams[3]=new NBATeam("Philadelphia","76ers",R.drawable.phili);
        teams[4]=new NBATeam("Toronto","Raptors",R.drawable.raptors);
        teams[5]=new NBATeam("Chicago","Bulls",R.drawable.bulls);
        teams[6]=new NBATeam("Cleveland","Cavaliers",R.drawable.cavs);
        teams[7]=new NBATeam("Detroit","Pistons",R.drawable.pistons);
        teams[8]=new NBATeam("Indiana","Pacers",R.drawable.pacers);
        teams[9]=new NBATeam("Milwaukee","Bucks",R.drawable.bucks);
        teams[10]=new NBATeam("Atlanta","Hawks",R.drawable.hawks);
        teams[11]=new NBATeam("Charlotte","Hornets",R.drawable.hornets);
        teams[12]=new NBATeam("Miami","Heat",R.drawable.heat);
        teams[13]=new NBATeam("Orlando","Magic",R.drawable.magic);
        teams[14]=new NBATeam("Washington","Wizards",R.drawable.wizards);
        teams[15]=new NBATeam("Denver","Nuggets",R.drawable.nuggets);
        teams[16]=new NBATeam("Minnesota","Timberwolves",R.drawable.timberwolves);
        teams[17]=new NBATeam("Oklahoma City","Thunder",R.drawable.thunder);
        teams[18]=new NBATeam("Portland","Trail Blazers",R.drawable.trailblazers);
        teams[19]=new NBATeam("Utah","Jazz",R.drawable.jazz);
        teams[20]=new NBATeam("Golden State","Warriors",R.drawable.warriors);
        teams[21]=new NBATeam("LA","Clippers",R.drawable.clippers);
        teams[22]=new NBATeam("Los Angeles","Lakers",R.drawable.lakers);
        teams[23]=new NBATeam("Sacramento","Kings",R.drawable.kings);
        teams[24]=new NBATeam("Phoenix","Suns",R.drawable.suns);
        teams[25]=new NBATeam("Dallas","Mavericks",R.drawable.mavericks);
        teams[26]=new NBATeam("Houston","Rockets",R.drawable.rockets);
        teams[27]=new NBATeam("Memphis","Grizzlies",R.drawable.grizzlies);
        teams[28]=new NBATeam("New Orleans","Pelicans",R.drawable.pelicans);
        teams[29]=new NBATeam("San Antonio","Spurs",R.drawable.spurs);
    }
    public Boolean isWordReal(String word)
    {
        word.toLowerCase();
        for(int i=0;i<teams.length&&teams[i]!=null;i++)
        {
            if(word.equals(teams[i].city_Name.toLowerCase())||word.equals(teams[i].brand_Name.toLowerCase()))
                return true;
        }
        return false;
    }
    public int GetImgUrl(String team)
    {
        team.toLowerCase();
        for(int i=0;i<teams.length&&teams[i]!=null;i++)
        {
            if(team.equals(teams[i].city_Name.toLowerCase())||team.equals(teams[i].brand_Name.toLowerCase()))
                return teams[i].imgurl;
        }
        return teams[0].imgurl;
    }
}
