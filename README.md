# Graficca
Projekt na zaliczenie Grafiki Komputerowej na Politechnice Białostockiej 2020/2021. 

## Autor
* [Daniel D](https://github.com/Rivijczyk98)

## Technologia
* Swing

## Funkcjonalności zawarte
### Zadanie 1
1. Rysowanie trzech prymitywów: linii, prostokątu, okręgu.
1. Podawanie parametrów rysowania za pomocą pola tekstowego (wpisanie parametrów w pole tekstowe i zatwierdzanie przyciskiem).
1. Rysowanie przy użyciu myszy (definiowanie punktów charakterystycznych kliknięciami).
1. Przesuwanie kształtów przy użyciu myszy (uchwycenie np. za krawędź i przeciągnięcie).
1. Zmiana kształtu / rozmiaru przy użyciu myszy (uchwycenie za punkty charakterystyczne i przeciągnięcie).
1. Zmiana kształtu / rozmiaru przy użyciu pola tekstowego (zaznaczenie obiektu i modyfikacja jego parametrów przy użyciu pola tekstowego).

### Zadanie 2
1. Wczytywanie i wyświetlanie plików graficznych w formacie PPM P3.
1. Wczytywanie i wyświetlanie plików graficznych w formacie PPM P6.
1. Obsługa błędów (odpowiednie komunikaty w przypadku błędów w pliku bądź w przypadku nieobsługiwanych formatów - np. P4, P5).
1. Wydajny sposób wczytywania plików (realizacja tego w sposób blokowy, a nie bajt po bajcie).
1. Skalowanie liniowe kolorów - w sytuacji w której wartość maksymalna wskazana w pliku jest różna od 255, muszą Państwo odpowiednio przeskalować tą wartość (przykładowo: (wartość * 255 ) / wartość_maks).
1. Wczytywanie plików JPEG oraz możliwość zapisu ówcześnie wczytanego obrazu w tym formacie.
1. Możliwość wyboru stopnia kompresji przy zapisie pliku do formatu JPEG.

### Zadanie 3
1. Dwie możliwości wyboru koloru przez użytkowników: RGB oraz CMYK (narzędzie do wyboru koloru może być wzorowane na popularnych programach graficznych).
1. Wybór koloru powinien odbywać się za pomocą myszy jak również poprzez wprowadzanie poszczególnych wartości w pole tekstowe.
1. Wybrany kolor powinien zostać zaprezentowany oraz przekonwertowany na drugi format, tzn. na CMYK przy wyborze RGB oraz na RGB przy wyborze CMYK przy użyciu wzorów podanych w treści prezentacji.
1. Wartości wybrane przez użytkownika oraz po konwersji powinny także zostać wyświetlone.

#### Rysowanie kostki RGB

1. Kostka RGB powinna zostać narysowana w trójwymiarze
1. Użytkownik powinien mieć możliwość obracania kostką.
1. Pokrycie kostki kolorami powinno odbywać się zgodnie ze wzorami skorelowanymi z przestrzenią barw RGB.

### Zadanie 4
#### Pierwsza część Państwa zadania jest skorelowana z przekształceniami punktowymi. Użytkownik musi mieć możliwość pierwotnego wczytania obrazu a następnie muszą być dla niego dostępne następujące operacje:
1. Dodawanie / odejmowanie
1. Mnożenie / dzielenie
1. Zmiana jasności obrazu (w sposób potęgowy lub logarytmiczny – inne rozwiązania nie będą akceptowane)
1. Konwersja do skali szarości

#### Druga część zadania jest z kolei związana z poprawą jakości obrazu. Użytkownik powinien mieć możliwość realizacji następujących operacji na obrazie:
1. Filtracji wygładzającej (uśrednionej), wyostrzającej oraz rozmycia gaussowskiego.
1. Filtracji medianowej
1. Detekcji krawędzi na obrazie z użyciem algorytmu Sobela
1. Wykonania splotu obrazu z maską o dowolnym rozmiarze (oczywiście rozmiar musi być zgodny z założeniami dotyczącymi masek!)

### Zadanie 5
#### Zadanie pierwsze: Napisz program, który na wczytanym obrazie zrealizuje następujące operacje:
1. Wyświetlenie histogramu dla wszystkich kanałów RGB
1. Rozciąganie histogramu (musi zostać zaprezentowany obraz wynikowy oraz histogram wynikowy)
1. Wyrównanie histogramu (musi zostać zaprezentowany obraz wynikowy oraz histogram wynikowy).

#### Zadanie drugie: Napisz program, który wykona binaryzację obrazu zgodnie z następującymi metodami:
1. Progowanie manualne
1. Progowanie z wykorzystaniem metody procentowej selekcji koloru czarnego.
1. Progowanie z wykorzystaniem metody Otsu.

### Zadanie 6
#### Zadanie pierwsze: Napisz program, który pozwoli na rysowanie wielomianowej krzywej Beziera.
1. Program może rysować krzywą Beziera o dowolnym stopniu; stopień rysowanej krzywej powinien zostać podany przez użytkownika,
1. Punkty charakterystyczne krzywej Beziera można podać podczas tworzenia za pomocą myszy lub przy pomocy pól tekstowych,
1. Punkty charakterystyczne krzywej Beziera można modyfikować za pomocą myszy (chwytanie i przeciąganie) oraz przy pomocy pól tekstowych,
1. Przy modyfikacji krzywej Beziera przy pomocy myszy zmiany na ekranie można obserwować na bieżąco – krzywa jest przeliczana w czasie rzeczywistym i zmiany są na bieżąco rysowane.

### Zadanie 7
#### Zadanie pierwsze: Napisz program, który pozwoli na definiowanie i rysowanie dowolnych figur (wielokątów) przy użyciu myszy oraz pól tekstowych. Dodatkowo program powinien umożliwić realizację następujących przekształceń na stworzonych figurach:
1. Przesunięcia o dowolny, zadany wektor.
1. Obrót względem wybranego punktu o zadany kąt.
1. Skalowanie względem wybranego punktu o zadany współczynnik.
1. Dodatkowo figury powinny być chwytane za pomocą myszy (co pozwala na ich wybór do modyfikacji).
1. Wszystkie operacje powinny być móc wykonywane z użyciem myszki i pól tekstowych.

### Zadanie 8 
#### Zadanie pierwsze: Państwa zadaniem jest pierwotne wczytanie obrazu (np. z rozszerzeniem jpeg) a następnie implementacja filtrów morfologicznych i zaprezentowanie ich działania na pierwotnie wczytanych danych.
1. Filtry, które muszą Państwo zrealizować to:
1. Dylatacja
1. Erozja
1. Otwarcie
1. Zamknięcie (Domknięcie)
1. Hit-or-Miss w kierunku pocienianie i pogrubiania.

### Zadanie 9
#### Zadanie pierwsze: Napisz program, który dokona analizy zdjęcia satelitarnego kampusu Politechniki Białostockiej pod kątem ilości terenów zielonych.
Przy ocenie brane pod uwagę będą:
1. Wczytywanie obrazu (musi istnieć możliwość wczytania różnych rodzajów obrazów – JPEG, TIFF, PNG…)
1. Obliczenie i jego dokładność, ile procent wczytanego obrazu stanowią tereny zielone.
1. Obliczenia powinny być wykonywane w sposób zautomatyzowany przy zachowaniu jak największej wydajności.
1. Muszą Państwo także zaimplementować możliwość parametryzacji programu – tj. program musi brać pod uwagę nie tylko tereny zielone ale także inne rodzaje kolorów bądź warunki wejściowe.
