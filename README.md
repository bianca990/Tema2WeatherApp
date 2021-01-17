# Aplicatia dezvoltata are ca scop afisarea datele meteorologice dintr-un oras selectat.🌧️


 Interfata grafica a fost dezvoltata folosind FXML. 
Pentru obtinerea datelor meteorologice a fost utilizat API-ul OpenWeatherMap.
(https://openweathermap.org)

## Tehnologii


```bash
Java 11.0.1
JavaFX 15.0.1
```

## Biblioteci

```python
GSON 2.8.6 (utilizata pentru procesarea structurilor JSON)

```

## Implementare ℹ️

Aplicatia este implementata pe structura pattern-ului Model-View-Controller(MVC) astfel:

	Partea de View, responsabila cu elemente din interfata utilizator, contine doua fisiere:
		- weatherView.fxml ce constituie grafica principala a aplicatiei (cele doua structuri ComboBox pentru selectia tarii respectiv orasului)
		- dialog-help.fxml reprezinta fereastra ce contine sugestii de utilizare a aplicatiei in cazul in care butonul HELP este apasat
	
	Partea de Model, ce contine datele si modul/regulile de procesare a acestora este divizata in mai multe parti:
		- partea de citire a fisierului de input (cel care contine denumirea tarilor si oraselor) si este compusa din clasa FileEntry (descrie datele
		  dintr-o line a fisierului de input - cityId, cityName, countryCode, etc) si clasa FileParser cea care proceseaza fisierul, compunand listele
		  de orase si tari.
		- partea de client (clasa WeatherClient) al API-ului OpenWeatherMap, cea care se ocupa cu:
			- constructia interogariilor bazei de date (clasele QueryBuilder si Query)
			- interogarea bazei de date OpenWeatherMap (clasa URLConnection)
			- parsarea JSON-ului primit ca raspuns folosind biblioteca gson. In urma procesarii, se va obtine o instanta a clasei CurrentWeather ce
			  va contine datele meteorologice pentru orasul interogat (temperatura, presiune, umiditate).

        Partea de Controler, responsabila cu gestiunea comunicatiei dintre model si view contine doua controllere:
	 	
		- DialogController
		- WeatherViewController ce implementeaza funtiile de callback pentru selectia unei tari/unui oras
