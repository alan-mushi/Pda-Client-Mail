package pda.datas;

public class TestConfig {
	public static void main(String[] args) {
		/* Test n°1 */
		Config testConfig1 = new Config();
		testConfig1.toXml();
		testConfig1.setAdresseServeur("test@test.com");
		testConfig1.setPortServeur("1283");
		testConfig1.setProxy(true);
		testConfig1.setAdresseProxy("proxy@proxy.com");
		testConfig1.setPortProxy("4520");
		
		System.out.println("Test n°1 (avec proxy) :");
		System.out.println(testConfig1.toXml());
		
		/* Test n°2 */
		
		Config testConfig2 = new Config();
		testConfig2.toXml();
		testConfig2.setAdresseServeur("test@test.com");
		testConfig2.setPortServeur("1283");
		
		System.out.println("Test n°2 (sans proxy et sans avoir spécifié qu'il n'y en avais pas') :");
		System.out.println(testConfig2.toXml());
		
		/* Test n°3 */
		
		Config testConfig3 = new Config();
		testConfig3.toXml();
		testConfig3.setAdresseServeur("test@test.com");
		testConfig3.setPortServeur("1283");
		testConfig3.setProxy(false);
		
		System.out.println("Test n°3 (sans proxy en ayant spécifié qu'il n'y en avait pas) :");
		System.out.println(testConfig3.toXml());
		
		/* Test 4 */
		
		Config testConfig4 = new Config();
		System.out.println("Test n°4 (fichier par défaut) :");
		System.out.println(testConfig4.toXml());
	}
}
