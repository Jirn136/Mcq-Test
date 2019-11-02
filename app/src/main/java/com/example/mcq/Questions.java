package com.example.mcq;

public class Questions {

    public String mQuestions[]={"What is full form of OS?",
            "Which is the A.I assistant for Apple?",
            "Who invented Ac Current?",
            "Which is the tallest mountain in the world?",
            "Which team is not in Indian Premier League?",
            "What is full form of IC Engines?",
            "When the partition of Bengal is held?",
            "When the second world war came to an end",
            "What is name of the nuke that exploded in Hiroshima?",
            "Which is the International Language?"};

    public String mOptions[][]={{"Operating System","Overloading System","On System","Over System"},
            {"Cortana","SIRI","ALEXA","Google Assitant"},
            {"JJ Thompson","Thomas Alva Edison","Nikola Tesla","Faraday"},
            {"Mt.Kilimanjaro","Mt.Etna","Mt.Rushmore","Mt.Everest"},
            {"Chennai Super Kings","Royal Challengers Banglore","Mumbai Indians","Lahore Lions"},
            {"Interference Combustion Engines","Internal Combustion Engines","Intra Combustion Engines","Induced Combustion Engines"},
            {"1935","1924","1900","1905"},
            {"1945","1954","1947","1938"},
            {"Bad Boy","Little Boy","Cute Boy","Worst Boy"},
            {"English","French","Japanese","Dutch"}};

    public String mAns[]={"Operating System","SIRI","Nikola Tesla","Mt.Everest","Lahore Lions","Internal Combustion Engines","1905","1945","Little Boy","English"};

    public String getQuestion(int i){
        String Question=mQuestions[i];
        return Question;
    }
    public String getChoice1(int i){
        String choice=mOptions[i][0];
        return choice;
    }
    public String getChoice2(int i){
        String choice=mOptions[i][1];
        return choice;
    }
    public String getChoice3(int i){
        String choice=mOptions[i][2];
        return choice;
    }
    public String getChoice4(int i){
        String choice=mOptions[i][3];
        return choice;
    }
    public String getCorrectAnswer (int i){
        String answer=mAns[i];
        return answer;
    }
}
