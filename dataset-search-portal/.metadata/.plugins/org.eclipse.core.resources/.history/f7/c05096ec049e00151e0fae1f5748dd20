function map_initialize() {
	alert("map_initialize");
				var infowindow = new InfoBubble({
					content: '',
					padding: 2,
					borderWidth: 1,
					borderRadius: 6,
					borderColor: "#007A45",
					arrowSize: 10,
			        borderWidth: 1,
			        arrowPosition: 30,
			        arrowStyle: 2,
			        shadowStyle: 1,
			        backgroundColor: "#F0FFE6"
				});
				var cwc2011_venue_data = [
					{
						name: "Museum fur Naturkunde - Leibniz-Institut fur Evolutions-und Biodiversitatsforschung an der Humboldt-Universitat zu Berlin",
						latlng: new google.maps.LatLng(52.531626,13.379459),
						shortName: "MfN",
						url: "http://www.naturkundemuseum-berlin.de/en/",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;Museum fur Naturkunde - Leibniz-Institut fur Evolutions-und Biodiversitatsforschung an der Humboldt-Universitat zu Berlin&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;MfN&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://www.naturkundemuseum-berlin.de/en/\" target=\"_blank\"&gt;http://www.naturkundemuseum-berlin.de/en/&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "Tartu Ulikool",
						latlng: new google.maps.LatLng(58.381345,26.719544),
						shortName: "UTARTU",
						url: "http://www.ut.ee/en",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;Tartu Ulikool&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;UTARTU&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://www.ut.ee/en\" target=\"_blank\"&gt;http://www.ut.ee/en&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "Itä-Suomen yliopisto",
						latlng: new google.maps.LatLng(62.609772,29.751263),
						shortName: "UEF",
						url: "http://www.uef.fi/uef/home",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;Itä-Suomen yliopisto&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;UEF&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://www.uef.fi/uef/home\" target=\"_blank\"&gt;http://www.uef.fi/uef/home&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "Global Biodiversity Information Facility",
						latlng: new google.maps.LatLng(55.703008,12.559383),
						shortName: "GBIF",
						url: "http://www.gbif.org/",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;Global Biodiversity Information Facility&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;GBIF&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://www.gbif.org/\" target=\"_blank\"&gt;http://www.gbif.org/&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "University of Leeds",
						latlng: new google.maps.LatLng(53.804212,-1.549762),
						shortName: "UnivLeeds",
						url: "http://www.leeds.ac.uk/",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;University of Leeds&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;UnivLeeds&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://www.leeds.ac.uk/\" target=\"_blank\"&gt;http://www.leeds.ac.uk/&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "Helmholtz – Zentrum für Umweltforschung",
						latlng: new google.maps.LatLng(51.352702,12.428834),
						shortName: "GMBH-UFZ",
						url: "http://www.ufz.de/",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;Helmholtz – Zentrum für Umweltforschung&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;GMBH-UFZ&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://www.ufz.de/\" target=\"_blank\"&gt;http://www.ufz.de/&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "Agencia Estatal Consejo Superior de Investigaciones Cientificas",
						latlng: new google.maps.LatLng(40.444204,-3.686113),
						shortName: "CSIC",
						url: "http://www.csic.es/web/guest/home",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;Agencia Estatal Consejo Superior de Investigaciones Cientificas&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;CSIC&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://www.csic.es/web/guest/home\" target=\"_blank\"&gt;http://www.csic.es/web/guest/home&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "The Chancellor, Masters and Scholars of the University of Cambridge",
						latlng: new google.maps.LatLng(52.206397,0.116801),
						shortName: "UCAM",
						url: "http://www.cam.ac.uk/",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;The Chancellor, Masters and Scholars of the University of Cambridge&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;UCAM&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://www.cam.ac.uk/\" target=\"_blank\"&gt;http://www.cam.ac.uk/&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "Centre National de la Recherche Scientifique",
						latlng: new google.maps.LatLng(48.850936,2.263842),
						shortName: "CNRS",
						url: "http://www.cnrs.fr/",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;Centre National de la Recherche Scientifique&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;CNRS&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://www.cnrs.fr/\" target=\"_blank\"&gt;http://www.cnrs.fr/&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "Pensoft Publishers Ltd",
						latlng: new google.maps.LatLng(42.657255,23.338301),
						shortName: "Pensoft",
						url: "http://www.pensoft.net/",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;Pensoft Publishers Ltd&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;Pensoft&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://www.pensoft.net/\" target=\"_blank\"&gt;http://www.pensoft.net/&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "Senckenberg Gesellscaft für Naturforschung",
						latlng: new google.maps.LatLng(50.117716,8.65203),
						shortName: "SGN",
						url: "http://www.senckenberg.de/",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;Senckenberg Gesellscaft für Naturforschung&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;SGN&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://www.senckenberg.de/\" target=\"_blank\"&gt;http://www.senckenberg.de/&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "Vizzuality sl",
						latlng: new google.maps.LatLng(40.719559,-73.999638),
						shortName: "Vizzuality",
						url: "http://vizzuality.com/",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;Vizzuality sl&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;Vizzuality&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://vizzuality.com/\" target=\"_blank\"&gt;http://vizzuality.com/&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "Fishbase Information &amp; Research Group Inc",
						latlng: new google.maps.LatLng(14.169991,121.254525),
						shortName: "FIN",
						url: "http://www.fin.ph/",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;Fishbase Information &amp; Research Group Inc&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;FIN&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://www.fin.ph/\" target=\"_blank\"&gt;http://www.fin.ph/&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "Hellenic Centre for Marine Research",
						latlng: new google.maps.LatLng(37.727212,23.908002),
						shortName: "HCMR",
						url: "http://www.hcmr.gr/en/",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;Hellenic Centre for Marine Research&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;HCMR&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://www.hcmr.gr/en/\" target=\"_blank\"&gt;http://www.hcmr.gr/en/&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "Natural History Museum",
						latlng: new google.maps.LatLng(51.496454,-0.176475),
						shortName: "NHM",
						url: "http://www.nhm.ac.uk/",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;Natural History Museum&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;NHM&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://www.nhm.ac.uk/\" target=\"_blank\"&gt;http://www.nhm.ac.uk/&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "Freie Universitat Berlin",
						latlng: new google.maps.LatLng(52.447771,13.280447),
						shortName: "FUB-BGBM",
						url: "http://www.fu-berlin.de/en/",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;Freie Universitat Berlin&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;FUB-BGBM&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://www.fu-berlin.de/en/\" target=\"_blank\"&gt;http://www.fu-berlin.de/en/&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "Kobenhavns Universitet",
						latlng: new google.maps.LatLng(55.680198,12.540715),
						shortName: "UCPH",
						url: "http://www.ku.dk/english/",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;Kobenhavns Universitet&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;UCPH&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://www.ku.dk/english/\" target=\"_blank\"&gt;http://www.ku.dk/english/&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "Musee Royal da l'Afrique Centrale",
						latlng: new google.maps.LatLng(50.849307,4.370234),
						shortName: "MRAC",
						url: "http://www.africamuseum.be/home",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;Musee Royal da l'Afrique Centrale&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;MRAC&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://www.africamuseum.be/home\" target=\"_blank\"&gt;http://www.africamuseum.be/home&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "Plazi GMBH",
						latlng: new google.maps.LatLng(46.935173,7.441539),
						shortName: "Plazi",
						url: "http://plazi.org/",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;Plazi GMBH&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;Plazi&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://plazi.org/\" target=\"_blank\"&gt;http://plazi.org/&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "Gluecad Ltd",
						latlng: new google.maps.LatLng(32.785958,34.991602),
						shortName: "GlueCAD",
						url: "http://www.gluecad.com/en-AboutUs.html",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;Gluecad Ltd&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;GlueCAD&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://www.gluecad.com/en-AboutUs.html\" target=\"_blank\"&gt;http://www.gluecad.com/en-AboutUs.html&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "Institute for European Environmental Policy, London",
						latlng: new google.maps.LatLng(51.500515,-0.13332),
						shortName: "IEEP",
						url: "http://www.ieep.eu/",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;Institute for European Environmental Policy, London&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;IEEP&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://www.ieep.eu/\" target=\"_blank\"&gt;http://www.ieep.eu/&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "Fundacao Amazonica de Defesa da Biosfera Associacao Privada",
						latlng: new google.maps.LatLng(-3.078509,-59.975021),
						shortName: "FDB-INPA",
						url: "http://www.fdb.org.br/",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;Fundacao Amazonica de Defesa da Biosfera Associacao Privada&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;FDB-INPA&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://www.fdb.org.br/\" target=\"_blank\"&gt;http://www.fdb.org.br/&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "Naturhistoriska Riksmuseet",
						latlng: new google.maps.LatLng(59.367986,18.052658),
						shortName: "NRM",
						url: "http://www.nrm.se/",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;Naturhistoriska Riksmuseet&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;NRM&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://www.nrm.se/\" target=\"_blank\"&gt;http://www.nrm.se/&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "Botanicky Ustav Slovenskej Akademie Vied",
						latlng: new google.maps.LatLng(48.168504,17.073931),
						shortName: "IBSAS",
						url: "http://ibot.sav.sk/?lng=en",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;Botanicky Ustav Slovenskej Akademie Vied&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;IBSAS&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://ibot.sav.sk/?lng=en\" target=\"_blank\"&gt;http://ibot.sav.sk/?lng=en&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "Centre Tecnologic Forestal de Catalunya",
						latlng: new google.maps.LatLng(42.011805,1.519489),
						shortName: "EBCC–CTFC",
						url: "http://www.ctfc.cat/?lang=en",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;Centre Tecnologic Forestal de Catalunya&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;EBCC–CTFC&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://www.ctfc.cat/?lang=en\" target=\"_blank\"&gt;http://www.ctfc.cat/?lang=en&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "Norwegian Biodiversity Information Centre",
						latlng: new google.maps.LatLng(63.429267,10.388296),
						shortName: "NBIC",
						url: "http://www.biodiversity.no/",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;Norwegian Biodiversity Information Centre&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;NBIC&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://www.biodiversity.no/\" target=\"_blank\"&gt;http://www.biodiversity.no/&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "Fondazione Edmund Mach",
						latlng: new google.maps.LatLng(46.194389,11.13655),
						shortName: "FEM",
						url: "http://www.iasma.it/",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;Fondazione Edmund Mach&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;FEM&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://www.iasma.it/\" target=\"_blank\"&gt;http://www.iasma.it/&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "Terradata",
						latlng: new google.maps.LatLng(43.145031,10.855853),
						shortName: "TerraData",
						url: "http://www.terradata.it/",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;Terradata&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;TerraData&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://www.terradata.it/\" target=\"_blank\"&gt;http://www.terradata.it/&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "Accademia Europea per la Ricerca Applicata ed il Perfezionamento Professionale Bolzano (Accademia Europea Bolzano)",
						latlng: new google.maps.LatLng(46.494404,11.347028),
						shortName: "EURAC",
						url: "http://www.eurac.edu/it/eurac/welcome/default.html",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;Accademia Europea per la Ricerca Applicata ed il Perfezionamento Professionale Bolzano (Accademia Europea Bolzano)&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;EURAC&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://www.eurac.edu/it/eurac/welcome/default.html\" target=\"_blank\"&gt;http://www.eurac.edu/it/eurac/welcome/default.html&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "UNEP-WCMC",
						latlng: new google.maps.LatLng(52.221385,0.096295),
						shortName: "WCMC",
						url: "http://www.unep-wcmc.org/",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;UNEP-WCMC&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;WCMC&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://www.unep-wcmc.org/\" target=\"_blank\"&gt;http://www.unep-wcmc.org/&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					},
					{
						name: "Estación Biológica de Doñana CSIC",
						latlng: new google.maps.LatLng(37.404324,-6.008406),
						shortName: "EBC-CSIC",
						url: "http://www.ebd.csic.es/",
						infoWindowContent: "&lt;div&gt;&lt;div&gt;&lt;h2&gt;Estación Biológica de Doñana CSIC&lt;/h2&gt;&lt;/div&gt;"+
						"&lt;div&gt;&lt;table&gt;"+
						"&lt;tr&gt;&lt;td&gt;&lt;img src=\"images/logotipoEBDCSICp.jpg\"/&gt;&lt;/td&gt;"+
						"&lt;td&gt;&lt;table&gt;&lt;tr&gt;&lt;td/&gt;&lt;td&gt;Nombre corto:&lt;/td&gt;&lt;td&gt;EBC-CSIC&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;tr&gt;&lt;td/&gt;&lt;td&gt;URL:&lt;/td&gt;&lt;td&gt;&lt;a href=\"http://www.ebd.csic.es/\" target=\"_blank\"&gt;http://www.ebd.csic.es/&lt;a/&gt;&lt;/td&gt;&lt;/tr&gt;"+
						"&lt;/table&gt;&lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;&lt;/div&gt;&lt;/div&gt;"
					}
				];
				var map = new google.maps.Map(document.getElementById("map-canvas"), {
					center: new google.maps.LatLng(40.23, -4.01),
			        zoom: 5,
			        mapTypeId: google.maps.MapTypeId.SATELLITE
				});
				for (var i = 0; i &lt; cwc2011_venue_data.length; i++) {
					var iwcontent = cwc2011_venue_data[i].infoWindowContent;
					var marker = new google.maps.Marker({
						position: cwc2011_venue_data[i].latlng,
						map: map,
						title: cwc2011_venue_data[i].name
					});
					(function(marker, iwcontent){                       
				        google.maps.event.addListener(marker, 'click', function() {
				            infowindow.setContent(iwcontent);
				            infowindow.open(map, marker);
				        });
				    })(marker,iwcontent);
				}
				var latlngbounds = new google.maps.LatLngBounds();
				for (var i = 0; i &lt; cwc2011_venue_data.length; i++) {
					latlngbounds.extend(cwc2011_venue_data[i].latlng);
				}
				rectangle = new google.maps.Rectangle({
					bounds: latlngbounds,
					map: map,
					fillColor: "#000000",
					fillOpacity: 0.0,
					strokeWeight: 0.5,
					draggable: true,
					editable: true
				});
				map.fitBounds(latlngbounds);

				var getRectangleDiv = document.createElement('div');
				var getRectangleControl = new GetRectangleControl(getRectangleDiv, map);

				getRectangleDiv.index = 1;
				map.controls[google.maps.ControlPosition.TOP_RIGHT].push(getRectangleDiv);
			}
			google.maps.event.addDomListener(window, 'load', map_initialize);
