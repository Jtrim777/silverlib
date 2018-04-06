//package silverlib.log;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//import java.io.*;
//
//import datetime as dt
//
//
//public class Logger {
//
//    public FileOutputStream outStream;
//    public String programName;
//
//    public DateTimeFormatter longFormat;
//    public DateTimeFormatter shortFormat;
//
//    public Logger(String logFile, String programNm) throws FileNotFoundException {
//        outStream = new FileOutputStream(logFile,true);
//
//        programName = programNm;
//
//        longFormat = DateTimeFormatter()
//
//        String timestamp = LocalDateTime.now().toString();
//    }
//
//        self.logFile.write("<<New %s session opened at %s>>\n\n"%(self.programName,
//
//    str(dt.datetime.now())))
//
//    def log(self, msg, subprocess=False, error=False):
//    pStr =""
//
//    timeFormat ="%H:%M:%S"
//    now =dt.datetime.now()
//
//    pStr +=now.strftime(timeFormat)
//    pStr +="| "
//
//            if subprocess:
//    pStr +="\t>> "
//
//            if error:
//    pStr +="ERRROR -- ! "
//
//            if
//    not error
//    and not
//    subprocess and '#'
//    not in
//    msg:
//    pStr +="$ "
//
//    pStr +="%s\n"%msg
//
//        self.logFile.write(pStr)
//            self.logFile.flush()
//
//    def end(self):
//            self.logFile.write("<<%s session ended at %s>>\n"%(self.programName,
//
//    str(dt.datetime.now())))
//            self.logFile.write("~~~~~~~~~~~~~~~~~~~~~\n\n\n")
//
//            self.logFile.close()
//}
