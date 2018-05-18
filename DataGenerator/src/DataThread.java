import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

import org.fluttercode.datafactory.impl.DataFactory;

public class DataThread implements Runnable{

  protected BlockingQueue<String> blockingQueue = null;
  DataFactory df = new DataFactory();

  public DataThread(BlockingQueue<String> blockingQueue){
    this.blockingQueue = blockingQueue;     
  }

  @Override
  public void run() {
	  //https://www.nasdaq.com/screening/companies-by-industry.aspx?exchange=NYSE&pagesize=200&page=10
	  String[] STOCK = {"APPL","GOOGL","MOTO","YAHO","REDF","SNY","DELL","SMSNG","ORCL","MTOR",
			  "ABB","AEE","IX","MRT","MPW","MED","MCC","MCV","MCX","MDLX",
			  "OHI","OMC","OMN","OOMA","OPY","ORCL","ORAN","OA","OEC","ORN",
			  "JMM","NHA","NXJ","NNY","NAN","NNC","ODC","ORI","OMAA","JLS",
			  "AFB","JHY","JHD","JHB","NXC","NXN","NID","NUM","NMS","NOM",
			  "AEG","PGEM","JCE","JCO","JQC","JDD","JFR","NKG","JGH","JHA",
			  "ADT","ADNT","POST","NAT","JWN","NOA","NWE","NCLH","NAC","NTC",
			  "ANET","PES","MAV","MHI","PXD","PJC","PAA","PLNT","PLT","PAH",
			  "AMN","PTY","PCN","PCI","PFL","PFN","PMF","PML","PMX","PNF",
			  "PM","FENG","DOC","PDM","PIR","PCK","PGTI","PHH","PBR","SEND",
			  "RSO","QSR","REV","REVG","RXN","RH","RBA","RAD","RIO","SSL",
			  "AOD","COO","RUBI","THR","TMO","TCRZ","TIER","TIF","TSU","TKR",
			  "AZUL","GIM","THC","TNC","TEN","TEX","TTI","TEVA","TPL","TX",
			  "ABT","ANF","ABBV","AAN","AAP","ASX","ASIX","TEF","TDA","TDE",
			  "LCM","ACM","AVK","HIVE","MITT","AGCO","AFL","NIE","NCZ","DDD",
			  "BABA","ALE","AOI","LNT","CBH","NCV","ADS","ALL","ALSN","NFJ",
			  "MMM","WUBA","ATEN","AAC","COLD","AMN","APU","AWK","WAGE","HCC",
			  "HCC","WPG","WBC","STAG","SSI","SMP","SXI","SWK","SWP","",
			  "STO","SPLP","SCS","SCA","STL","SF","SFB","EDI","STOR","STON",
			  "SUI","SMLP","SUM","GJO","SMFG","INN","SYK","EDF","GJO","SRI",
			  "SUN","SHO","TPR","TGT","TCO","TTM","TOO","GCI","TGNA","TRC",
			  };
	  
	  String header = "STOCK, ASK_PRICE, BID_PRICE, OPEN_PRICE, CLOSE_PRICE, VOLUME, DATE";
	  String dataWithNewLine=header+System.getProperty("line.separator");
	  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	  Date minDate = df.getDate(2000, 1, 1);
	  Date maxDate = df.getDate(2018, 1, 1);
	  //Date nextDate = df.getDate(minDate, 0, 1);
      try {
			blockingQueue.put(dataWithNewLine);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    for (;;) { 
    	Date nextDate = df.getDateBetween(minDate, maxDate);
        
        String data = df.getItem(STOCK) + ", "+ df.getNumberBetween(0, 10000)+ ", "+ df.getNumberBetween(0, 10000)+ ", "+ df.getNumberBetween(0, 10000);
        data = data + ", "+ df.getNumberBetween(0, 10000)+ ", "+ df.getNumberBetween(0, 10000) + ", " + formatter.format(nextDate);

        dataWithNewLine=data+System.getProperty("line.separator");
        //minDate = nextDate;
        try {
			blockingQueue.put(dataWithNewLine);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //System.out.print(dataWithNewLine);
    }
  }
}