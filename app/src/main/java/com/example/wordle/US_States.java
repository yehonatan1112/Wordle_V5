package com.example.wordle;

public class US_States {
    String[] states=new String[50];
    public US_States()
    {
        states[0]="Alaska";
        states[1]="Montana";
        states[2]="Nevada";
        states[3]="New Hampshire";
        states[4]="New Jersey";
        states[5]="New York";
        states[6]="Oregon";
        states[7]="Washington";
        states[8]="California";
        states[9]="Arizona";
        states[10]="Utah";
        states[11]="Colorado";
        states[12]="New Mexico";
        states[13]="Nebraska";
        states[14]="Minnesota";
        states[15]="Wisconsin";
        states[16]="Illinois";
        states[17]="North Dakota";
        states[18]="South Dakota";
        states[19]="Idaho";
        states[20]="Iowa";
        states[21]="Ohio";
        states[22]="Indiana";
        states[23]="Michigan";
        states[24]="Kansas";
        states[25]="Arkansas";
        states[26]="Texas";
        states[27]="Hawaii";
        states[28]="Florida";
        states[29]="Louisiana";
        states[30]="Georgia";
        states[31]="Maine";
        states[32]="Vermont";
        states[33]="Rhode Island";
        states[34]="Delaware";
        states[35]="North Carolina";
        states[36]="South Carolina";
        states[37]="West Virginia";
        states[38]="Virginia";
        states[39]="Alabama";
        states[40]="Wyoming";
        states[41]="Connecticut";
        states[42]="Maryland";
        states[43]="Kentucky";
        states[44]="Oklahoma";
        states[45]="Mississippi";
        states[46]="Missouri";
        states[47]="Massachusetts";
        states[48]="Pennsylvania";
        states[49]="Tennessee";
    }
    public Boolean isWordReal(String word)
    {
        word.toLowerCase();
        for(int i=0;i<states.length&&states[i]!=null;i++)
        {
            if(word.equals(states[i].toLowerCase()))
                return true;
        }
        return false;
    }
}
