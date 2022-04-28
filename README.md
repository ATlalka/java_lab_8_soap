# Java lab 8 - Soap
Aplikacja zrobiona w ramach przedmiotu "Programowanie w języku Java - techniki zaawansowane"  

## Informacje:

Aplikacja została stworzona przy użyciu Spring Boota i bazuje na kodzie zrobionym w ramach laboratorium 7.  
Testowanie odbyło się z wykorzystaniem wtyczki Wizdler do Google Chrome.  
Po uruchomieniu aplikacji i wpisaniu w przeglądarce adresu:  
http://localhost:8080/ws/finances.wsdl  
można testować endpointy przy użyciu wyżej wymienionej wtyczki.  
  
## Treść zadania:

Zaimplementuj serwis komunikujący się protokołem SOAP, który pozwoli wykorzystać logikę biznesową (z warstwą persystencji) zaprojektowaną w ramach lab07. Serwis ten ma udostępniać API pozwalające na zarządzanie wydarzeniami, osobami, ratami, wpłatami. Klientem serwisu może być narzędzie SoapUI lub Postman (nie trzeba samemu implementować żadnego klienta).

Podczas implementacji można wykorzystać dowolną metodę przetwarzania komunikatów SOAP.
Z uwagi na dużą automatyzację zalecane jest wykorzystanie JAX-WS/Apache CXF. Można wybrać podejście bottomUp lub topDown.
Zalecane jest oparcie budowanego rozwiązania na czymś, co pozwoli obsłużyć opis interfejsu w języku WSDL.

Tworzony projekt może być projektem eclipsowym (DynamicWebProject) lub jeszcze lepiej - projektem mavenowym.

Istnieje niezły tutorial o tym, jak stworzyć projekt mavenowy, który korzysta z CXF (https://www.cse.unsw.edu.au/~cs9322/labs/lab01/index.html).
Jest też (może nawet lepszy) tutorial na stronie: https://www.tutorialspoint.com/apache_cxf/apache_cxf_with_wsdl_first.htm

Można też spróbować zrealizować laboratorium korzystając z Spring Boot (odpowiedni tutorial znajduje się pod adresem: https://www.baeldung.com/spring-boot-soap-web-service).
