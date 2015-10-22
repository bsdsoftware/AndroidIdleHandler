# AndroidIdleHandler
Gestisce l'idle dell'app per android

Esempio di utilizzo nel progetto AccertaMobile.

Utilizzo
-----
La libreria mette a disposizione un'activity che gestisce lo stato dell'idle.

Per utilizzare è necessario creare un'activity che estende IdleActivity e sovrascrivere i metodi 
```java

  @Override
    public long getTimeoutIdle() {
        return 1000*60;
    }

    @Override
    public void doOnIdle() {
        //TODO
    }
```
Il primo è il valore in millisecondi di tempo, che deve aspettare l'app prima di andare in idle.
Il secondo specifica le operazioni da eseguire quando l'app entra nello stato di idle.

NB: a seconda dei casi conviene chiamare il metodo super.stopTimer(); dentro al metodo onDestroy, per evitare crash.


Download
--------

Aggiungere al repository il percorso:
```groovy
repositories {
        jcenter()
        maven {
            url "http://dl.bintray.com/bsdsoftware/bsdsoftware"
        }
    }
```
e nel gradle file la dipendenza:
```groovy
compile 'it.bsdsoftware:bsd-idle-handler:1.0'
```
