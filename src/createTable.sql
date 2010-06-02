DROP TABLE ALBUM;
DROP TABLE ARTIST;
DROP TABLE LABEL;

DROP SEQUENCE SQ_ARTIST;
DROP SEQUENCE SQ_ALBUM;
DROP SEQUENCE SQ_LABEL;


CREATE TABLE ARTIST (
    AID NUMBER NOT NULL,
    NAME VARCHAR2(200) NOT NULL,
    COUNTRY VARCHAR2(200),
    INFO VARCHAR2(2000),
    CONSTRAINT ARTIST_PK PRIMARY KEY (AID)
);

CREATE TABLE LABEL (
    LID NUMBER NOT NULL,
    MAJOR NUMBER,
    NAME VARCHAR2(200) NOT NULL,
    LOGO VARCHAR2(200),
    INFO VARCHAR2(2000),
    CONSTRAINT LABEL_PK PRIMARY KEY (LID),
    CONSTRAINT LABEL_LABEL_FK FOREIGN KEY (MAJOR) REFERENCES LABEL (LID) ON DELETE CASCADE
);


CREATE TABLE ALBUM (
    ALID NUMBER NOT NULL,
    NAME VARCHAR2(200) NOT NULL,
    TYPE VARCHAR2(100) NOT NULL,
    RELEASE DATE NOT NULL,
    GENRE VARCHAR2(500),
    COVER VARCHAR2(200),
    REVIEW VARCHAR2(2000),
    ART NUMBER,
    LBL NUMBER,
    CONSTRAINT ALBUM_PK PRIMARY KEY (ALID),
    CONSTRAINT ALBUM_ARTIST_FK FOREIGN KEY (ART) REFERENCES ARTIST (AID) ON DELETE SET NULL,
    CONSTRAINT ALBUM_LABEL_FK FOREIGN KEY (LBL) REFERENCES LABEL (LID) ON DELETE SET NULL
);

CREATE SEQUENCE SQ_ARTIST
   MINVALUE 1
   MAXVALUE 10000
   START WITH 1
   INCREMENT BY 1;

CREATE SEQUENCE SQ_ALBUM
   MINVALUE 1
   MAXVALUE 10000
   START WITH 1
   INCREMENT BY 1;

CREATE SEQUENCE SQ_LABEL
   MINVALUE 1
   MAXVALUE 10000
   START WITH 1
   INCREMENT BY 1;

INSERT INTO ARTIST VALUES(SQ_ARTIST.nextval, 'Arctic Monkeys', 'England', 'Arctic Monkeys are an English indie rock band from High Green, a suburb of Sheffield, England. Formed in 2002, the band currently consists of Alex Turner, Jamie Cook, Nick O''Malley, Matt Helders and John Ashton.');   
INSERT INTO ARTIST VALUES(SQ_ARTIST.nextval, 'Razorlight', 'England', 'Razorlight is an English-Swedish indie rock band formed in 2002. They are primarily known in their home countries, having topped the charts with the 2006 single "America" and its parent self-titled album, their second. The band consists of lead singer and rhythmic guitarist Johnny Borrell, guitarist Bjorn Agren, bass guitarist Carl Gustaf Dalemo and drummer David Sullivan-Kaplan.');  
INSERT INTO ARTIST VALUES(SQ_ARTIST.nextval, 'The Kooks', 'England', 'The Kooks are an English indie rock band formed in Brighton, East Sussex, in 2004.[1] Formed by Luke Pritchard (vocals/guitar), Hugh Harris (lead guitar), Paul Garred (drums), and Max Rafferty (bass guitar), the lineup of the band remained constant until 2008 and the departure of Rafferty. Dan Logan was drafted in as a temporary replacement, until Peter Denton joined the band on a permanent basis in October 2008.');  
INSERT INTO ARTIST VALUES(SQ_ARTIST.nextval, 'The Fratellis', 'Scotland', 'The Fratellis are a Scottish alternative rock band from Glasgow, Scotland. The band consists of lead vocalist and guitarist Jon Fratelli (born John Lawler), bass guitarist Barry Fratelli (born Barry Wallace), and drummer, backing vocalist, occasional guitarist and banjo player Mince Fratelli (born Gordon McRory). During live shows, they are assisted by pianist/rhythm guitarist, Will Foster.');  
INSERT INTO ARTIST VALUES(SQ_ARTIST.nextval, 'Muse', 'England', 'Muse are an English rock band from Teignmouth, Devon. Since their inception, the band has been composed of Matthew Bellamy (lead vocals, guitar, piano), Christopher Wolstenholme (bass, backing vocals), and Dominic Howard (drums and percussion). Muse are known for their energetic and extravagant live performances and their fusion of many music genres, including progressive rock, classical music, heavy metal and electronica.');
INSERT INTO ARTIST VALUES(SQ_ARTIST.nextval, 'Franz Ferdinand', 'Scotland', 'Franz Ferdinand are a Scottish rock band that formed in Glasgow, Scotland, in 2002. The band is composed of Alex Kapranos (lead vocals and guitar), Bob Hardy (bass guitar), Nick McCarthy (rhythm guitar, keyboards and backing vocals) and Paul Thomson (drums, percussion and backing vocals).');


INSERT INTO LABEL VALUES (SQ_LABEL.nextval, NULL, 'Sony Music Entertainment', 'http://upload.wikimedia.org/wikipedia/en/thumb/3/34/Sony_BMG.svg/225px-Sony_BMG.svg.png','Sony BMG Music Entertainment was a global recorded music company, which was a 5050 joint venture between the Sony Corporation of America and Bertelsmann AG. The ventures successor, the again-active Sony Music Entertainment, is 100% owned by the Sony Corporation of America.');
INSERT INTO LABEL VALUES (SQ_LABEL.nextval, NULL, 'EMI', 'http://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/EMI_logo.svg/200px-EMI_logo.svg.png','The EMI Group (Electric Musical Industries Ltd.) is a British music company. It is the fourth-largest business group and family of record labels in the recording industry, making it one of the "big four" record companies and a member of the RIAA. EMI Group also has a major publishing arm - EMI Music Publishing - based in New York City. The company was once a constituent of the FTSE 100 Index but is now wholly owned by Terra Firma Capital Partners.');
INSERT INTO LABEL VALUES (SQ_LABEL.nextval, NULL, 'Warner Music Group', 'http://upload.wikimedia.org/wikipedia/en/thumb/2/2d/Warner_Music_Group_logo.svg/200px-Warner_Music_Group_logo.svg.png','Warner Music Group is the third-largest business group and family of record labels in the recording industry, making it one of the big four record companies. The current incarnation of the company was formed in 2004 when it was spun off  from Time Warner, and as a result, Time Warner no longer retains any ownership. Warner Music Group also has a music publishing arm called Warner/Chappell Music, which is currently one of the worlds largest music-publishing companies.');
INSERT INTO LABEL VALUES (SQ_LABEL.nextval, NULL, 'Universal Music Group', 'http://upload.wikimedia.org/wikipedia/ru/thumb/b/bd/Universal_Music_Group.png/260px-Universal_Music_Group.png','Universal Music Group (UMG) is the largest business group and family of record labels in the recording industry. It is the largest of the "big four" record companies by its commanding market share and its multitude of global operations. Universal Music Group is a wholly owned subsidiary of international french media conglomerate Vivendi.Universal Music Group owns a music publisher, Universal Music Publishing Group, which became the worlds largest following the acquisition of BMG Music Publishing in May 2007.');
INSERT INTO LABEL VALUES (SQ_LABEL.nextval, NULL, 'Indie labels', 'http://upload.wikimedia.org/wikipedia/en/thumb/1/11/Umg.png/225px-Umg.png','indielabel');
INSERT INTO LABEL VALUES (SQ_LABEL.nextval, 1, 'Columbia/Epic Label Group', 'http://upload.wikimedia.org/wikipedia/en/1/13/Columbiaepiclabelgroup.png','Universal Music Group (UMG) is the largest business group and family of record labels in the recording industry. It is the largest of the "big four" record companies by its commanding market share and its multitude of global operations. Universal Music Group is a wholly owned subsidiary of international french media conglomerate Vivendi.Universal Music Group owns a music publisher, Universal Music Publishing Group, which became the worlds largest following the acquisition of BMG Music Publishing in May 2007.');
INSERT INTO LABEL VALUES (SQ_LABEL.nextval, 1, 'RCA/Jive Label Group', 'http://upload.wikimedia.org/wikipedia/en/0/04/RCA-JIVE_label_group_logo.gif','RCA Records (originally The Victor Talking Machine Company, then RCA Victor) is one of the flagship labels of Sony Music Entertainment. The RCA initials stand for Radio Corporation of America (later renamed RCA Corporation), which was the parent corporation from 1929[1]  to 1983 and a partner from 1983 to 1986.');
INSERT INTO LABEL VALUES (SQ_LABEL.nextval, 5, 'Domino Records', 'http://upload.wikimedia.org/wikipedia/en/7/76/Domin_p1.jpg','Domino Recording Company, generally known as Domino Records, is an independent record label based in London. There is also a wing of the label based in United States, releasing Domino artists music from the labels Brooklyn  offices and run by Kris Gillespie. In addition, Stephen Pastel presides over the subsidiary label Geographic Music, which releases more unusual British and World music.');

INSERT INTO ALBUM VALUES (SQ_ALBUM.nextval, 'Hamburg', 'cd', TO_DATE('24/08/2009','dd/mm/yyyy'),'rock','http://skachateto.com/images/97.jpg','Critical response to the album was extremely positive with a Metacritic rating of 82 and featuring highly in many year-end lists and being hailed as a modern classic. Many critics and figures in the British Media hyped the Arctic Monkeys and their rapid rise to acclaim through unconventional means and some even cited the Arctic Monkeys as revolutionising the way people find music as they built a fanbase on the basis of a few demos shared by fans through the internet. NME declared them Our Generations Most Important Band and Alex Turners lyrics and depiction of Sheffield and the night lives of teenagers in particular praised with him being labelled as a master of observation and USA Today claiming you probably wont hear better CD all year long and calling it utterly infectious. MusicOMH wrote that it was the sort of guitar rock that makes you fall in love with music all over again and along with many other critics cited A Certain Romance as the standout track and as being a wonderfully articulate dissection of youth culture that belies Turners tender years. It was however, noted that some of the tracks which had previously been released on the internet as demos had lost some of their quality and dont sound as good.', 1, 8);
INSERT INTO ALBUM VALUES (SQ_ALBUM.nextval, 'Franz Ferdinand', 'cd', TO_DATE('09/01/2004','dd/mm/yyyy'),'indie rock','http://ecx.images-amazon.com/images/I/418y9j6-QWL._SS500_.jpg','Franz Ferdinand received universal critical acclaim. The NME said that the band was the latest in the line of art school rock bands featuring The Beatles, The Rolling Stones, The Who, Duran Duran, Roxy Music, the Sex Pistols, Wire and Blur. It rated the album as 9 out of 10 and said: This album is the latest and most intoxicating example of the wonderful pushing its way up between the ugly slabs of Pop Idol, nu metal and Britons aping American bands. What these blossoming bands have in common is the absolute conviction that rock n roll is more than a career option.', 6, 8);
INSERT INTO ALBUM VALUES (SQ_ALBUM.nextval, 'Showbiz', 'cd', TO_DATE('04/10/1999','dd/mm/yyyy'),'alternative rock','http://upload.wikimedia.org/wikipedia/en/9/9d/Museshowbizalbumcover.jpg','Showbiz is the debut studio album by English alternative rock band Muse, released in the United Kingdom on 4 October 1999. Recorded between April and May at RAK Studios and Sawmills Studio, respectively, the album was produced by John Leckie and Paul Reeve in conjunction with the band. Showbiz was a minor commercial success, reaching number 29 on the UK Albums Chart.', 5, 2);
INSERT INTO ALBUM VALUES (SQ_ALBUM.nextval, 'Origin of Symmetry', 'cd', TO_DATE('17/06/2001','dd/mm/yyyy'),'alternative rock','http://upload.wikimedia.org/wikipedia/en/3/3a/Museoriginofsymemtryalbumcover.jpg','Origin of Symmetry is the second studio album by English alternative rock band Muse, released on 17 June 2001 by Mushroom Records. Recording took place at Ridge Farm Studios in Surrey and Real World Studio in Wiltshire, and additional recordings were made at Astoria Studios, Richmond Studios and Abbey Road Studios in London and Sawmills Studio in Fowey, Cornwall. The album was mixed at Sawmills and mastered at Sony Music Studios in London. Origin of Symmetry was produced by David Bottrill, John Leckie (who previously worked on the bands first album, Showbiz) and the band themselves. In the UK it reached #3 and was certified platinum. The title for the album comes from a concept put forward by Michio Kaku in his book Hyperspace.', 5, 2);
INSERT INTO ALBUM VALUES (SQ_ALBUM.nextval, 'Absolution', 'cd', TO_DATE('21/09/2003','dd/mm/yyyy'),'alternative rock','http://upload.wikimedia.org/wikipedia/en/b/b4/Muse_-_Absolution_Cover_UK.jpg','Absolution is the third studio album by English alternative rock band Muse. It was released on 21 September 2003 in the UK and on March 23, 2004 in the U.S. by Taste Music Limited. The album yielded the bands first American hits – Time Is Running Out and Hysteria, the former becoming their first UK Top 10 single. In 2006 it was voted the 21st best British album ever. The album was placed in at #23 after a public vote for Q magazine in February 2008 for the Best British Albums of All Time. In 2009 it was voted by Kerrang! as the second-best album of the 21st century (thus far).', 5, 2);
INSERT INTO ALBUM VALUES (SQ_ALBUM.nextval, 'Black Holes and Revelations', 'cd', TO_DATE('03/06/2006','dd/mm/yyyy'),'alternative rock','http://upload.wikimedia.org/wikipedia/en/8/8f/BHARCOVER.jpg','Black Holes and Revelations is the fourth studio album by English rock band Muse, released on 3 July 2006. Recording was split between New York and France, and it was the first time Muse had taken a more active role in the albums production. The album was a change in style from Muses previous albums, and the band cited influences that included Queen, Millionaire, Sly and the Family Stone, Depeche Mode, Franz Ferdinand and music from southern Italy.Black Holes and Revelations was placed at #34 in a public vote by Q Magazine for The Best British Albums of all time in February 2008.The album has political and sci-fi undertones, with the lyrics covering topics as varied as political corruption, alien invasion, New World Order conspiracies as well as more conventional love songs.', 5, 2);
INSERT INTO ALBUM VALUES (SQ_ALBUM.nextval, 'Konk', 'cd', TO_DATE('11/05/2008','dd/mm/yyyy'),'rock','http://upload.wikimedia.org/wikipedia/en/8/87/The_kooks_front.jpg','Konk is the second album by English indie rock band The Kooks, released on April 14, 2008 on Virgin Records. Produced by Tony Hoffer, the album is named after the London studio where it was recorded, which is the property of Ray Davies. It was also released in a double disc version featuring a second CD, entitled RAK, which includes seven new songs not included on the album, an alternate version of the album track See the Sun, recorded at RAK recording studios, and a demo track called Brooklyn. This second album was named after Konk Studios, where it was recorded. The special edition RAK features the same album cover, apart from the blue Konk sign being red. It also seems that Konk has vocals provided by Luke Pritchard whilst RAK seems to be mainly Hugh Harris on vocals.', 3, 3);
INSERT INTO ALBUM VALUES (SQ_ALBUM.nextval, 'Costello Music', 'cd', TO_DATE('30/10/2006','dd/mm/yyyy'),'rock','http://upload.wikimedia.org/wikipedia/en/c/c8/TheFratellis-CostelloMusic.jpg','Costello Music is the debut album by Scottish rock band The Fratellis. It was released on 30 October 2006 on Fallout Records and was a moderate success, peaking at #2 in the UK Albums Chart and spent 83 weeks in the Top 100. It debuted behind FutureSex/LoveSounds by Justin Timberlake and stayed in the #2 position for two more weeks, this time behind Ta-Dah by Scissor Sisters. The album had five single releases, as well as the download-only EP Flathead. Chelsea Dagger was the most successful single, peaking at #5 in the UK and #4 in the Netherlands, but the other singles failed to chart in most countries.', 4, 4);
INSERT INTO ALBUM VALUES (SQ_ALBUM.nextval, 'Here We Stand', 'cd', TO_DATE('10/08/2008','dd/mm/yyyy'),'rock','http://upload.wikimedia.org/wikipedia/en/3/32/TheFratellis_-_HereWeStand.jpg','Despite some negative reviews from critics and fans, Here We Stand reached and peaked at number five in the UK Albums Chart on 15 June 2008.  Each single released off the album included 3 B-Sides (except for A Heady Tale which only included 2). Tell Me a Lie was featured on the EA Sports title FIFA 09, while My Friend John was on Forza Motorsport 3, in a cover by Lance Hayes.', 4, 4);
INSERT INTO ALBUM VALUES (SQ_ALBUM.nextval, 'Whatever People Say I Am, Thats What Im Not', 'cd', TO_DATE('23/07/2006','dd/mm/yyyy'),'rock','http://upload.wikimedia.org/wikipedia/en/7/74/Wpsiatwin.jpg','Whatever People Say I Am, Thats What Im Not is the debut album by English band Arctic Monkeys, released on 23 January 2006. The album became the UKs fastest selling debut album, shifting over 360,000 copies in its first week, and remains the fastest selling debut album by a band. It has since gone quadruple platinum in the UK, and won the 2006 Mercury Prize.The album includes both tracks from the bands original EP, Five Minutes with Arctic Monkeys, as well as their first two singles and UK Number Ones, I Bet You Look Good on the Dancefloor and When the Sun Goes Down.', 1, 8);
INSERT INTO ALBUM VALUES (SQ_ALBUM.nextval, 'You Could Have It So Much Better', 'cd', TO_DATE('03/10/2005','dd/mm/yyyy'),'rock','http://upload.wikimedia.org/wikipedia/en/2/2a/Better.PNG','You Could Have It So Much Better is the second studio album by Glaswegian indie rock band Franz Ferdinand that was first released 3 October 2005 in the United Kingdom. Recorded in both the United States and their own studio in Glasgow with producer Rich Costey, the album gave birth to the release of four UK Top 30 singles Do You Want To on 23 August 2005, The Fallen / L. Wells, Walk Away and Eleanor Put Your Boots On. The album became the bands first UK #1 album and although their debut album was relatively successful in the USA, going platinum and reaching #32, You Could Have It So Much Better managed to reach #8 and earned gold status in the States. The cover design is modeled on Alexander Rodchenkos 1924 portrait of Lilya Brik. It was produced by Rick Costey and Franz Ferdinand.', 6, 8);


commit;

