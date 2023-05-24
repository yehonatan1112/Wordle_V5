package com.example.wordle;

import com.google.android.gms.maps.model.LatLng;

import java.util.Random;

public class Countries {
    Country[] countries=new Country[220];
    public Countries()
    {
        countries[0]=new Country("Russia",17098242,R.drawable.russia,145934462,R.drawable.russiaflag,new LatLng(55.7522,37.6155));
        countries[1]=new Country("Canada",9984670,R.drawable.canada,37742154,R.drawable.canadaflag,new LatLng(45.424721, -75.695000));
        countries[2]=new Country("China",9706961,R.drawable.china,1439323776,R.drawable.chinaflag,new LatLng(39.9289,116.3883));
        countries[3]=new Country("United States",9372610,R.drawable.usa,331002651,R.drawable.usaflag,new LatLng(40.730610, -73.935242));
        countries[4]=new Country("Brazil",8515767,R.drawable.brazil,212559417,R.drawable.brazilflag,new LatLng(-15.7833,-47.9161));
        countries[5]=new Country("Australia",7692024,R.drawable.australia,25499884,R.drawable.australiaflag,new LatLng(-35.283,149.129));
        countries[6]=new Country("India",3287590,R.drawable.india,1380004385,R.drawable.indiaflag,new LatLng(28.6,77.2));
        countries[7]=new Country("Argentina",2780400,R.drawable.argentina,45195774,R.drawable.argentinaflag,new LatLng(-34.6025,-58.3975));
        countries[8]=new Country("Kazakhstan",2724900,R.drawable.kazakhstan,18776707,R.drawable.kazakhstanflag,new LatLng(51.1811,71.4278));
        countries[9]=new Country("Algeria",2381741,R.drawable.algeria,37742154,R.drawable.algeriaflag,new LatLng(36.7631,3.0506));
        countries[10]=new Country("DR Congo",2344858,R.drawable.drcongo,89561403,R.drawable.drcongoflag,new LatLng(-4.3297,15.315));
        countries[11]=new Country("Greenland",2166086	,R.drawable.greenland,56770,R.drawable.greenlandflag,new LatLng(64.166666, -51.7333304));
        countries[12]=new Country("Saudi Arabia",2149690,R.drawable.saudiarabia,34813871,R.drawable.saudiarabiaflag,new LatLng(24.6408,46.7727));
        countries[13]=new Country("Mexico",1964375,R.drawable.mexico,128932753,R.drawable.mexicoflag,new LatLng(19.4424,-99.131));
        countries[14]=new Country("Indonesia",1904569,R.drawable.indonesia,273523615,R.drawable.indonesiaflag,new LatLng(-6.1744,106.8294));
        countries[15]=new Country("Sudan",1886068,R.drawable.sudan,43849260,R.drawable.sudanflag,new LatLng(15.5881,32.5342));
        countries[16]=new Country("Libya",1759540,R.drawable.libya,6871292,R.drawable.libyaflag,new LatLng(32.8925,13.18));
        countries[17]=new Country("Iran",1648195,R.drawable.iran,83992949,R.drawable.iranflag,new LatLng(35.6719,51.4243));
        countries[18]=new Country("Mongolia",1564110,R.drawable.mongolia,3278290,R.drawable.mongoliaflag,new LatLng(47.9167,106.9166));
        countries[19]=new Country("Peru",1285216,R.drawable.peru,32971854,R.drawable.peruflag,new LatLng(-12.048,-77.0501));
        countries[20]=new Country("Chad",1284000,R.drawable.chad,16425864,R.drawable.chadflag,new LatLng(12.1131,15.0491));
        countries[21]=new Country("Niger",1267000,R.drawable.niger,24206644,R.drawable.nigerflag,new LatLng(13.5167,2.1167));
        countries[22]=new Country("Angola",1246700,R.drawable.angola,32866272,R.drawable.angolaflag,new LatLng(-8.8383,13.2344));
        countries[23]=new Country("Mali",1240192,R.drawable.mali,20250833,R.drawable.maliflag,new LatLng(12.65,-8.0));
        countries[24]=new Country("South Africa",1221037,R.drawable.southafrica,59308690,R.drawable.southafricaflag,new LatLng(-33.92,18.435));
        countries[25]=new Country("Colombia",1141748,R.drawable.colombia,50882891,R.drawable.colombiaflag,new LatLng(4.5964,-74.0833));
        countries[26]=new Country("Ethiopia",1104300,R.drawable.ethiopia,114963588,R.drawable.ethiopiaflag,new LatLng(9.0333,38.7));
        countries[27]=new Country("Bolivia",1098581,R.drawable.bolivia,11673021,R.drawable.boliviaflag,new LatLng(-16.498,-68.15));
        countries[28]=new Country("Mauritania",1030700,R.drawable.mauritania,4649658,R.drawable.mauritaniaflag,new LatLng(18.0864,-15.9753));
        countries[29]=new Country("Egypt",1002450,R.drawable.egypt,102334404,R.drawable.egyptflag,new LatLng(30.05,31.25));
        countries[30]=new Country("Tanzania",945087,R.drawable.tanzania,59734218,R.drawable.tanzaniaflag,new LatLng(-6.8,39.2683));
        countries[31]=new Country("Nigeria",923768,R.drawable.nigeria,206139589,R.drawable.nigeriaflag,new LatLng(9.0833,7.5333));
        countries[32]=new Country("Venezuela",916445,R.drawable.venezuela,28435940,R.drawable.venezuelaflag,new LatLng(10.501,-66.917));
        countries[33]=new Country("Pakistan",881912,R.drawable.pakistan,220892340,R.drawable.pakistanflag,new LatLng(33.7,73.1666));
        countries[34]=new Country("Namibia",825615,R.drawable.namibia,2540905,R.drawable.namibiaflag,new LatLng(-22.57,17.0835));
        countries[35]=new Country("Mozambique",801590,R.drawable.mozambique,31255435,R.drawable.mozambiqueflag,new LatLng(-25.9553,32.5892));
        countries[36]=new Country("Turkey",783562,R.drawable.turkey,84339067,R.drawable.turkeyflag,new LatLng(39.9272,32.8644));
        countries[37]=new Country("Chile",756102,R.drawable.chile,19116201,R.drawable.chileflag,new LatLng(-33.45,-70.667));
        countries[38]=new Country("Zambia",752612,R.drawable.zambia,18383955,R.drawable.zambiaflag,new LatLng(-15.4166,28.2833));
        countries[39]=new Country("Myanmar",676578,R.drawable.myanmar,54409800,R.drawable.myanmarflag,new LatLng(19.7666,96.1186));
        countries[40]=new Country("Afghanistan",652230,R.drawable.afghanistan,38928346,R.drawable.afghanistanflag,new LatLng(34.5167,69.1833));
        countries[41]=new Country("Somalia",637657,R.drawable.somalia,15893222,R.drawable.somaliaflag,new LatLng(2.0667,45.3667));
        countries[42]=new Country("Cen. Afr. Republic",622984,R.drawable.centralafricanrepublic,4829767,R.drawable.centralafricanrepublicflag,new LatLng(4.3666,18.5583));
        countries[43]=new Country("South Sudan",619745,R.drawable.southsudan,11193725,R.drawable.southsudanflag,new LatLng(4.83,31.58));
        countries[44]=new Country("Ukraine",603500,R.drawable.ukraine,43733762,R.drawable.ukraineflag,new LatLng(50.4334,30.5166));
        countries[45]=new Country("Madagascar",587041,R.drawable.madagascar,27691018,R.drawable.madagascarflag,new LatLng(-18.9166,47.5166));
        countries[46]=new Country("Botswana",582000,R.drawable.botswana,2351627,R.drawable.botswanaflag,new LatLng(-24.6463,25.9119));
        countries[47]=new Country("Kenya",580367	,R.drawable.kenya,53771296,R.drawable.kenyaflag,new LatLng(-1.2833,36.8167));
        countries[48]=new Country("France",551695,R.drawable.france,65273511,R.drawable.franceflag,new LatLng(48.8667,2.3333));
        countries[49]=new Country("Yemen",527968,R.drawable.yemen,29825964,R.drawable.yemenflag,new LatLng(15.3547,44.2066));
        countries[50]=new Country("Thailand",513120,R.drawable.thailand,69799978,R.drawable.thailandflag,new LatLng(13.75,100.5166));
        countries[51]=new Country("Spain",505992,R.drawable.spain,46754778,R.drawable.spainflag,new LatLng(40.4,-3.6834));
        countries[52]=new Country("Turkmenistan",488100,R.drawable.turkmenistan,6031200,R.drawable.turkmenistanflag,new LatLng(37.95,58.3833));
        countries[53]=new Country("Cameroon",475442	,R.drawable.cameroon,26545863,R.drawable.cameroonflag,new LatLng(3.8667,11.5167));
        countries[54]=new Country("Papua New Guinea",462840,R.drawable.papuanewguinea,8947024,R.drawable.papuanewguineaflag,new LatLng(-9.4647,147.1925));
        countries[55]=new Country("Sweden",450295,R.drawable.sweden,10099265,R.drawable.swedenflag,new LatLng(59.3508,18.0973));
        countries[56]=new Country("Uzbekistan",447400,R.drawable.uzbekistan,33469203,R.drawable.uzbekistanflag,new LatLng(41.3117,69.2949));
        countries[57]=new Country("Morocco",446550,R.drawable.morocco,36910560,R.drawable.moroccoflag,new LatLng(34.0253,-6.8361));
        countries[58]=new Country("Iraq",438317,R.drawable.iraq,40222493,R.drawable.iraqflag,new LatLng(35.6719,51.4243));
        countries[59]=new Country("Paraguay",406752,R.drawable.paraguay,7132538,R.drawable.paraguayflag,new LatLng(-25.2964,-57.6415));
        countries[60]=new Country("Zimbabwe",390757,R.drawable.zimbabwe,14862924,R.drawable.zimbabweflag,new LatLng(-17.8178,31.0447));
        countries[61]=new Country("Japan",377930,R.drawable.japan,126476461,R.drawable.japanflag,new LatLng(35.685,139.7514));
        countries[62]=new Country("Germany",357114,R.drawable.germany,83783942,R.drawable.germanyflag,new LatLng(52.5218,13.4015));
        countries[63]=new Country("Philippines",342353,R.drawable.philippines,109581078,R.drawable.philippinesflag,new LatLng(14.6042,120.9822));
        countries[64]=new Country("Congo",342000,R.drawable.congo,5518087,R.drawable.congoflag,new LatLng(-4.2592,15.2847));
        countries[65]=new Country("Finland",338424,R.drawable.finland,5540720,R.drawable.finlandflag,new LatLng(60.1756,24.9341));
        countries[66]=new Country("Vietnam",331212,R.drawable.vietnam,97338579,R.drawable.vietnamflag,new LatLng(21.0333,105.85));
        countries[67]=new Country("Malaysia",330803,R.drawable.malaysia,32365999,R.drawable.malaysiaflag,new LatLng(3.1667,101.7));
        countries[68]=new Country("Norway",323802,R.drawable.norway,5421241,R.drawable.norwayflag,new LatLng(59.9167,10.75));
        countries[69]=new Country("Côte d'Ivoire",322463,R.drawable.cotedivoire,26378274,R.drawable.cotedivoireflag,new LatLng(6.8184,-5.2755));
        countries[70]=new Country("Poland",312679,R.drawable.poland,37846611,R.drawable.polandflag,new LatLng(52.25,21.0));
        countries[71]=new Country("Oman",309500,R.drawable.oman,5106626,R.drawable.omanflag,new LatLng(23.6133,58.5933));
        countries[72]=new Country("Italy",301336,R.drawable.italy,60461826,R.drawable.italyflag,new LatLng(41.896,12.4833));
        countries[73]=new Country("Ecuador",276841,R.drawable.ecuador,17643054,R.drawable.ecuadorflag,new LatLng(-0.215,-78.5001));
        countries[74]=new Country("Burkina Faso",272967,R.drawable.burkinafaso,20903273,R.drawable.burkinafasoflag,new LatLng(12.3703,-1.5247));
        countries[75]=new Country("New Zealand",270467,R.drawable.newzealand,4822233,R.drawable.newzealandflag,new LatLng(-41.3,174.7833));
        countries[76]=new Country("Gabon",267668,R.drawable.gabon,2225734,R.drawable.gabonflag,new LatLng(0.3854,9.458));
        countries[77]=new Country("Western Sahara",266000,R.drawable.westernsahara,597339,R.drawable.westernsaharaflag,new LatLng(27.154339, -13.199891));
        countries[78]=new Country("Guinea",245857,R.drawable.guinea,13132795,R.drawable.guineaflag,new LatLng(9.5315,-13.6802));
        countries[80]=new Country("United Kingdom",242900,R.drawable.uk,67886011,R.drawable.ukflag,new LatLng(51.5,-0.1167));
        countries[81]=new Country("Uganda",241550,R.drawable.uganda,45741007,R.drawable.ugandaflag,new LatLng(0.3167,32.5833));
        countries[82]=new Country("Ghana",238533,R.drawable.ghana,31072940,R.drawable.ghanaflag,new LatLng(5.55,-0.2167));
        countries[83]=new Country("Romania",238391,R.drawable.romania,19237691,R.drawable.romaniaflag,new LatLng(44.4334,26.0999));
        countries[84]=new Country("Laos",236800,R.drawable.laos,7275560,R.drawable.laosflag,new LatLng(17.9667,102.6));
        countries[85]=new Country("Guyana",214969,R.drawable.guyana,786552,R.drawable.guyanaflag,new LatLng(6.802,-58.167));
        countries[86]=new Country("Belarus",207600,R.drawable.belarus,9449323,R.drawable.belarusflag,new LatLng(53.9,27.5666));
        countries[87]=new Country("Kyrgyzstan",199951,R.drawable.kyrgyzstan,6524195,R.drawable.kyrgyzstanflag,new LatLng(42.8731,74.5852));
        countries[88]=new Country("Senegal",196722,R.drawable.senegal,16743927,R.drawable.senegalflag,new LatLng(14.7158,-17.4731));
        countries[89]=new Country("Syria",185180,R.drawable.syria,17500658,R.drawable.syriaflag,new LatLng(	33.5,36.3));
        countries[90]=new Country("Cambodia",181035,R.drawable.cambodia,16718965,R.drawable.cambodiaflag,new LatLng(11.55,104.9166));
        countries[91]=new Country("Uruguay",181034,R.drawable.uruguay,3473730,R.drawable.uruguayflag,new LatLng(-34.858,-56.1711));
        countries[92]=new Country("Suriname",163820,R.drawable.suriname,586632,R.drawable.surinameflag,new LatLng(5.835,-55.167));
        countries[93]=new Country("Tunisia",163610,R.drawable.tunisia,11818619,R.drawable.tunisiaflag,new LatLng(36.8028,10.1797));
        countries[94]=new Country("Bangladesh",147570,R.drawable.bangladesh,164689383,R.drawable.bangladeshflag,new LatLng(23.7231,90.4086));
        countries[95]=new Country("Nepal",147181,R.drawable.nepal,29136808,R.drawable.nepalflag,new LatLng(27.7167,85.3166));
        countries[96]=new Country("Tajikistan",143100,R.drawable.tajikistan,9537645,R.drawable.tajikistanflag,new LatLng(38.56,68.7739));
        countries[97]=new Country("Greece",131990,R.drawable.greece,10423054,R.drawable.greeceflag,new LatLng(37.9833,23.7333));
        countries[98]=new Country("North Korea",130373,R.drawable.northkorea,25778816,R.drawable.northkoreaflag,new LatLng(39.0194,125.7547));
        countries[99]=new Country("Malawi",118484,R.drawable.malawi,19129952,R.drawable.malawiflag,new LatLng(-13.9833,33.7833));
        countries[100]=new Country("Eritrea",117600,R.drawable.eritrea,3546421,R.drawable.eritreaflag,new LatLng(15.3333,38.9333));
        countries[101]=new Country("Benin",112622,R.drawable.benin,12123200,R.drawable.beninflag,new LatLng(6.4833,2.6166));
        countries[102]=new Country("Honduras",112492,R.drawable.honduras,9904607,R.drawable.hondurasflag,new LatLng(14.102,-87.2175));
        countries[103]=new Country("Liberia",111369,R.drawable.liberia,5057681,R.drawable.liberiaflag,new LatLng(6.3106,-10.8048));
        countries[104]=new Country("Bulgaria",110879	,R.drawable.bulgaria,6948445,R.drawable.bulgariaflag,new LatLng(42.6833,23.3167));
        countries[105]=new Country("Cuba",109884,R.drawable.cuba,11326616,R.drawable.cubaflag,new LatLng(23.132,-82.3642));
        countries[106]=new Country("Guatemala",108889,R.drawable.guatemala,17915568,R.drawable.guatemalaflag,new LatLng(14.6211,-90.527));
        countries[107]=new Country("Iceland",103000,R.drawable.iceland,341243,R.drawable.icelandflag,new LatLng(64.15,-21.95));
        countries[108]=new Country("South Korea",100210,R.drawable.southkorea,51269185,R.drawable.southkoreaflag,new LatLng(37.5663,126.9997));
        countries[109]=new Country("Hungary",93028,R.drawable.hungary,9660351,R.drawable.hungaryflag,new LatLng(47.5,19.0833));
        countries[110]=new Country("Portugal",92090,R.drawable.portugal,10196709,R.drawable.portugalflag,new LatLng(38.7227,-9.1449));
        countries[111]=new Country("Jordan",89342,R.drawable.jordan,10203134,R.drawable.jordanflag,new LatLng(31.95,35.9333));
        countries[112]=new Country("Serbia",88361,R.drawable.serbia,8737371,R.drawable.serbiaflag,new LatLng(44.8186,20.468));
        countries[113]=new Country("Azerbaijan",86600,R.drawable.azerbaijan,10139177,R.drawable.azerbaijanflag,new LatLng(40.3953,49.8622));
        countries[114]=new Country("Austria",83871,R.drawable.austria,9006398,R.drawable.austriaflag,new LatLng(48.2,16.3666));
        countries[115]=new Country("The Emirates",83600,R.drawable.uae,9890402,R.drawable.uaeflag,new LatLng(24.4667,54.3666));
        countries[116]=new Country("French Guiana",83534,R.drawable.frenchguiana,298682,R.drawable.frenchguianaflag,new LatLng(4.937200, -52.326000));
        countries[117]=new Country("Czechia",78865,R.drawable.czechia,10708981,R.drawable.czechiaflag,new LatLng(50.0833,14.466));
        countries[118]=new Country("Panama",75417,R.drawable.panama,4314767,R.drawable.panamaflag,new LatLng(8.968,-79.533));
        countries[119]=new Country("Sierra Leone",71740,R.drawable.sierraleone,7976983,R.drawable.sierraleoneflag,new LatLng(8.47,-13.2342));
        countries[120]=new Country("Ireland",70273,R.drawable.ireland,4937786,R.drawable.irelandflag,new LatLng(53.3331,-6.2489));
        countries[121]=new Country("Georgia",69700,R.drawable.georgia,3989167,R.drawable.georgiaflag,new LatLng(41.725,44.7908));
        countries[122]=new Country("Sri Lanka",65610,R.drawable.srilanka,21413249,R.drawable.srilankaflag,new LatLng(6.932,79.8578));
        countries[123]=new Country("Lithuania",65300,R.drawable.lithuania,2722289,R.drawable.lithuaniaflag,new LatLng(54.6834,25.3166));
        countries[124]=new Country("Latvia",64559,R.drawable.latvia,1886198,R.drawable.latviaflag,new LatLng(56.95,24.1));
        countries[125]=new Country("Togo",56785,R.drawable.togo,8278724,R.drawable.togoflag,new LatLng(6.1319,1.2228));
        countries[126]=new Country("Croatia",56594,R.drawable.croatia,4105267,R.drawable.croatieflag,new LatLng(45.8,16.0));
        countries[127]=new Country("Bosnia and Herzegovina",51209,R.drawable.bosnia,3280819,R.drawable.bosniaflag,new LatLng(43.85,18.383));
        countries[128]=new Country("Costa Rica",51100,R.drawable.costarica,5094118,R.drawable.costaricaflag,new LatLng(9.935,-84.0841));
        countries[129]=new Country("Slovakia",49037,R.drawable.slovakia,5459642,R.drawable.slovakiaflag,new LatLng(48.15,17.117));
        countries[130]=new Country("Dominican Republic",48671,R.drawable.dominicanrepublic,10847910,R.drawable.dominicanrepublicflag,new LatLng(18.4701,-69.9001));
        countries[131]=new Country("Estonia",45227,R.drawable.estonia,1326535,R.drawable.estoniaflag,new LatLng(59.4339,24.728));
        countries[132]=new Country("Denmark",43094,R.drawable.denmark,5792202,R.drawable.denmarkflag,new LatLng(55.6786,12.5635));
        countries[133]=new Country("Netherlands",41850,R.drawable.netherlands,17134872,R.drawable.netherlandsflag,new LatLng(52.08,4.27));
        countries[134]=new Country("Switzerland",41284,R.drawable.switzerland,8654622,R.drawable.switzerlandflag,new LatLng(46.9167,7.467));
        countries[135]=new Country("Bhutan",38394,R.drawable.bhutan,771608,R.drawable.bhutanflag,new LatLng(27.473,89.639));
        countries[136]=new Country("Taiwan",36193,R.drawable.taiwan,23816775,R.drawable.taiwanflag,new LatLng(25.0358,121.5683));
        countries[137]=new Country("Guinea-Bissau",36125,R.drawable.guineabissau,1968001,R.drawable.guineabissauflag,new LatLng(11.865,-15.5984));
        countries[138]=new Country("Moldova",33846,R.drawable.moldova,4033963,R.drawable.moldovaflag,new LatLng(47.005,28.8577));
        countries[139]=new Country("Belgium",30528,R.drawable.belgium,11589623,R.drawable.belgiumflag,new LatLng(50.8333,4.3333));
        countries[140]=new Country("Lesotho",30355,R.drawable.lesotho,2142249,R.drawable.lesothoflag,new LatLng(-29.3167,27.4833));
        countries[141]=new Country("Armenia",29743,R.drawable.armenia,2963243,R.drawable.armeniaflag,new LatLng(40.1812,44.5136));
        countries[142]=new Country("Solomon Islands",28896,R.drawable.solomonislands,686884,R.drawable.solomonislandsflag,new LatLng(-9.438,159.9498));
        countries[143]=new Country("Albania",28748,R.drawable.albania,2877797,R.drawable.albaniaflag,new LatLng(41.3275,19.8189));
        countries[144]=new Country("Equatorial Guinea",28051,R.drawable.equatorialguinea,1402985,R.drawable.equatorialguineaflag,new LatLng(3.75,8.7833));
        countries[145]=new Country("Burundi",27834,R.drawable.burundi,11890784,R.drawable.burundiflag,new LatLng(-3.3761,29.36));
        countries[146]=new Country("Haiti",27750,R.drawable.haiti,11402528,R.drawable.haitiflag,new LatLng(18.541,-72.336));
        countries[147]=new Country("Rwanda",26338,R.drawable.rwanda,12952218,R.drawable.rwandaflag,new LatLng(-1.9536,30.0605));
        countries[148]=new Country("North Macedonia",25713,R.drawable.northmacedonia,2083374,R.drawable.northmacedoniaflag,new LatLng(42.0,21.4335));
        countries[149]=new Country("Djibouti",23200,R.drawable.djibouti,988000,R.drawable.djiboutiflag,new LatLng(11.595,43.148));
        countries[150]=new Country("Belize",22966,R.drawable.belize,397628,R.drawable.belizeflag,new LatLng(17.252,-88.7671));
        countries[151]=new Country("El Salvador",21041,R.drawable.elsalvador,6486205,R.drawable.elsalvadorflag,new LatLng(13.71,-89.203));
        countries[152]=new Country("Israel",20770,R.drawable.israel,8655535,R.drawable.israelflag,new LatLng(31.7784,35.2066));
        countries[153]=new Country("Palestine",0,R.drawable.palestine,0,R.drawable.palestine,new LatLng(56.894662, 3.397245));
        countries[154]=new Country("Slovenia",20273,R.drawable.slovenia,2078938,R.drawable.sloveniaflag,new LatLng(46.0553,14.515));
        countries[155]=new Country("New Caledonia",18575,R.drawable.newcaledonia,285498,R.drawable.newcaledoniaflag,new LatLng(-22.275801, 166.457993));
        countries[156]=new Country("Fiji",18272,R.drawable.fiji,896445,R.drawable.fijiflag,new LatLng(-18.133,178.4417));
        countries[157]=new Country("Kuwait",17818,R.drawable.kuwait,4270571,R.drawable.kuwaitflag,new LatLng(29.3697,47.9783));
        countries[158]=new Country("Eswatini",17364,R.drawable.eswatini,1160164,R.drawable.eswatiniflag,new LatLng(-26.4667,31.2));
        countries[159]=new Country("Timor-Leste",14874,R.drawable.timorleste,1318445,R.drawable.timorlesteflag,new LatLng(-8.5594,125.5795));
        countries[160]=new Country("Bahamas",13943,R.drawable.bahamas,393244,R.drawable.bahamasflag,new LatLng(25.0834,-77.35));
        countries[161]=new Country("Montenegro",13812,R.drawable.montenegro,628066,R.drawable.montenegroflag,new LatLng(42.466,19.2663));
        countries[162]=new Country("Vanuatu",12189,R.drawable.vanuatu,307145,R.drawable.vanuatuflag,new LatLng(-17.7338100, 168.3218800));
        countries[164]=new Country("Falkland Islands",12173,R.drawable.falklandislands,3480,R.drawable.falklandislandsflag,new LatLng(-51.6886, -57.8553));
        countries[165]=new Country("Qatar",11586,R.drawable.qatar,2881053,R.drawable.qatarflag,new LatLng(25.2866,51.533));
        countries[166]=new Country("Jamaica",10991,R.drawable.jamaica,2961167,R.drawable.jamaicaflag,new LatLng(17.9771,-76.7674));
        countries[167]=new Country("Gambia",10689,R.drawable.gambia,2416668,R.drawable.gambiaflag,new LatLng(13.4539,-16.5917));
        countries[168]=new Country("Lebanon",10452,R.drawable.lebanon,6825445,R.drawable.lebanonflag,new LatLng(33.872,35.5097));
        countries[169]=new Country("Cyprus",9251,R.drawable.cyprus,1207359,R.drawable.cyprusflag,new LatLng(35.1667,33.3666));
        countries[170]=new Country("Puerto Rico",8870,R.drawable.puertorico,2860853,R.drawable.puertoricoflag,new LatLng(18.456285, -66.070518));
        countries[171]=new Country("Brunei",5765,R.drawable.brunei,437479,R.drawable.bruneiflag,new LatLng(4.8833,114.9333));
        countries[172]=new Country("Trinidad & Tobago",5130,R.drawable.trinidadandtobago,1399488,R.drawable.trinidadandtobagoflag,new LatLng(10.652,-61.517));
        countries[173]=new Country("French Polynesia",4167,R.drawable.frenchpolynesia,280908,R.drawable.frenchpolynesiaflag,new LatLng(-17.53733, -149.5665));
        countries[174]=new Country("Cabo Verde",4033,R.drawable.caboverde,555987,R.drawable.caboverdeflag,new LatLng(14.9167,-23.5167));
        countries[175]=new Country("Samoa",2842,R.drawable.samoa,198414,R.drawable.samoaflag,new LatLng(-14.274,-170.7046));
        countries[176]=new Country("Luxembourg",2586,R.drawable.luxembourg,625978,R.drawable.luxembourgflag,new LatLng(49.6117,6.13));
        countries[177]=new Country("Réunion",2511,R.drawable.reunion,895312,R.drawable.reunionflag,new LatLng(-20.878901, 55.448101));
        countries[178]=new Country("Mauritius",2040,R.drawable.mauritius,1271768,R.drawable.mauritiusflag,new LatLng(-20.1666,57.5));
        countries[179]=new Country("Comoros",1862,R.drawable.comoros,869601,R.drawable.comorosflag,new LatLng(-11.7042,43.2402));
        countries[180]=new Country("Guadeloupe",1628,R.drawable.guadeloupe,400124,R.drawable.guadeloupeflag,new LatLng(15.99854,-61.72548));
        countries[181]=new Country("Faeroe Islands",1393,R.drawable.faeroeislands,48863,R.drawable.faeroeislandsflag,new LatLng(62.0097, -6.7716));
        countries[182]=new Country("Martinique",1128,R.drawable.martinique,375265,R.drawable.martiniqueflag,new LatLng(14.600000, -61.083302));
        countries[183]=new Country("Hong Kong SAR",1104,R.drawable.hongkong,7496981,R.drawable.hongkongflag,new LatLng(22.302711, 114.177216));
        countries[184]=new Country("Sao Tome and Principe",964,R.drawable.saotomeandprincipe,219159,R.drawable.saotomeandprincipeflag,new LatLng(0.3334,6.7333));
        countries[185]=new Country("Turks & Caicos Islands",948,R.drawable.turksandcaicosislands,38717,R.drawable.turksandcaicosislandsflag,new LatLng(21.4612, -71.1419));
        countries[186]=new Country("Kiribati",811,R.drawable.kiribati,119449,R.drawable.kiribatiflag,new LatLng(1.3382,173.0176));
        countries[187]=new Country("Bahrain",765,R.drawable.bahrain,1701575,R.drawable.bahrainflag,new LatLng(26.2361,50.5831));
        countries[188]=new Country("Dominica",751,R.drawable.dominica,71986,R.drawable.dominicaflag,new LatLng(45.424721, 75.695000));
        countries[189]=new Country("Tonga",747,R.drawable.tonga,105695,R.drawable.tongaflag,new LatLng(15.301,-61.387));
        countries[190]=new Country("Singapore",710,R.drawable.singapore,5850342,R.drawable.singaporeflag,new LatLng(1.293,103.8558));
        countries[191]=new Country("Micronesia",702,R.drawable.micronesia,548914,R.drawable.micronesiaflag,new LatLng(6.9166,158.15));
        countries[192]=new Country("Saint Lucia",616,R.drawable.saintlucia,183627,R.drawable.saintluciaflag,new LatLng(14.002,-61.0));
        countries[193]=new Country("Isle of Man",572,R.drawable.isleofman,85033,R.drawable.isleofmanflag,new LatLng(54.15, -4.48333));
        countries[194]=new Country("Guam",549,R.drawable.guam,168775,R.drawable.guamflag,new LatLng(13.4745,144.7504));
        countries[195]=new Country("Andorra",468,R.drawable.andorra,77265,R.drawable.andorraflag,new LatLng(42.5,1.5165));
        countries[196]=new Country("North Mariana Islands",464,R.drawable.northernmarianaislands,57559,R.drawable.northernmarianaislandsflag,new LatLng(15.2137,145.7546));
        countries[197]=new Country("Palau",459,R.drawable.palau,18094,R.drawable.palauflag,new LatLng(7.5,134.6242));
        countries[198]=new Country("Seychelles",452,R.drawable.seychelles,98347,R.drawable.seychellesflag,new LatLng(-4.6166,55.45));
        countries[199]=new Country("Curaçao",444,R.drawable.curacao,164093,R.drawable.curacaoflag,new LatLng(12.2004,-69.02));
        countries[200]=new Country("Antigua & Barbuda",442,R.drawable.antiguaandbarbuda,97929,R.drawable.antiguaandbarbudaflag,new LatLng(17.118,-61.85));
        countries[201]=new Country("Barbados",430,R.drawable.barbados,287375,R.drawable.barbadosflag,new LatLng(13.102,-59.6165));
        countries[202]=new Country("Saint Helena",394,R.drawable.sainthelena,6077,R.drawable.sainthelenaflag,new LatLng(-15.928634, -5.715175));
        countries[203]=new Country("Saint Vincent",389,R.drawable.saintvincent,110940,R.drawable.saintvincentflag,new LatLng(13.1483,-61.2121));
        countries[204]=new Country("Mayotte",374,R.drawable.mayotte,272815,R.drawable.mayotteflag,new LatLng(-12.780949, 45.227872));
        countries[205]=new Country("US Virgin Islands",347,R.drawable.usvirginislands,104425,R.drawable.usvirginislandsflag,new LatLng(18.3419, -64.9307));
        countries[206]=new Country("Grenada",344,R.drawable.grenada,112523,R.drawable.grenadaflag,new LatLng(12.0526,-61.7416));
        countries[207]=new Country("Caribbean Netherlands",328,R.drawable.caribbeannetherlands,26223,R.drawable.caribbeannetherlandsflag,new LatLng(12.201890, -68.262383));
        countries[208]=new Country("Malta",316,R.drawable.malta,441543,R.drawable.maltaflag,new LatLng(35.8997,14.5147));
        countries[209]=new Country("Maldives",300,R.drawable.maldives,540544,R.drawable.maldivesflag,new LatLng(4.1667,73.4999));
        countries[210]=new Country("Cayman Islands",264,R.drawable.caymanislands,65722,R.drawable.caymanislandsflag,new LatLng(19.292997, -81.366806));
        countries[211]=new Country("Aruba",180,R.drawable.aruba,106766,R.drawable.arubaflag,new LatLng(12.510052, -70.009354));
        countries[212]=new Country("Liechtenstein",160,R.drawable.liechtenstein,38128,R.drawable.liechtensteinflag,new LatLng(47.141030, 9.520928));
        countries[213]=new Country("UK Virgin Islands",151,R.drawable.britishvirginislands,30231,R.drawable.britishvirginislandsflag,new LatLng(18.428611, -64.618466));
        countries[214]=new Country("San Marino",61,R.drawable.sanmarino,33931,R.drawable.sanmarinoflag,new LatLng(43.9172,12.4667));
        countries[215]=new Country("Bermuda",54,R.drawable.bermuda,62278,R.drawable.bermudaflag,new LatLng(32.2942,-64.7839));
        countries[216]=new Country("Macao",30,R.drawable.macao,649335,R.drawable.macaoflag,new LatLng(22.210928, 113.552971));
        countries[217]=new Country("Monaco",2,R.drawable.monaco,39242,R.drawable.monacoflag,new LatLng(43.733334, 7.416667));
        countries[218]=new Country("Gibraltar",6,R.drawable.gibraltar,33691,R.drawable.gibraltarflag,new LatLng(36.1320, -5.3529));
        countries[219]=new Country("Saint Martin",53,R.drawable.saintmartin,38666,R.drawable.saintmartinflag,new LatLng(18.0858547 ,-63.0618597));
    }
    public Country RandomCountry()
    {
        Country c;
        Random rand=new Random();
        int randint=rand.nextInt(219);
        if(countries[randint]!=null) {
            c = countries[randint];
            countries[randint] = null;
            return c;
        }
        else {
            while (countries[randint] == null)
                randint = rand.nextInt(219);
        }

        return countries[randint];
    }
    public String[] getArrayOfNames()
    {
        String[] Names=new String[countries.length];
        for(int i=0;i<countries.length;i++)
            Names[i]=countries[i].Name;
        return Names;
    }
    public int GetImgUrl(String country)
    {
        country.toLowerCase();
        for(int i=0;i<countries.length&&countries[i]!=null;i++)
        {
            if(country.equals(countries[i].Name.toLowerCase()))
                return countries[i].imgUrl;
        }
        for(int i=80;i<countries.length&&countries[i]!=null;i++)
        {
            if(country.equals(countries[i].Name.toLowerCase()))
                return countries[i].imgUrl;
        }
        for(int i=164;i<countries.length&&countries[i]!=null;i++)
        {
            if(country.equals(countries[i].Name.toLowerCase()))
                return countries[i].imgUrl;
        }
        return countries[152].imgUrl;
    }
    public String getRandomCountry(String protectedCountry,String protectedCountry2,String protectedCountry3)
    {
        String countryName="";
        Random rand=new Random();
        int randint=rand.nextInt(219);
        if(countries[randint]!=null&&!countries[randint].Name.equals(protectedCountry)&&!countries[randint].Name.equals(protectedCountry2)&&!countries[randint].Name.equals(protectedCountry3)) {
            countryName = countries[randint].Name;
            return countryName;
        }
        else {
            while (countries[randint] == null)
                randint = rand.nextInt(219);
        }

        return countries[randint].Name;

    }
    public Boolean isWordReal(String word)
    {
        for (int i=0;i<countries.length&&countries[i]!=null;i++)
        {
            if(countries[i].Name.toLowerCase().equals(word))
                return true;
        }
        for (int i=80;i<countries.length&&countries[i]!=null;i++)
        {
            if(countries[i].Name.toLowerCase().equals(word))
                return true;
        }
        for (int i=164;i<countries.length&&countries[i]!=null;i++)
        {
            if(countries[i].Name.toLowerCase().equals(word))
                return true;
        }
        return false;
    }
}
