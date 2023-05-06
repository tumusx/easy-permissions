# <n> EasyPermissions <n>
A Easy Permissions é uma biblioteca para Android que facilita a solicitação e gerenciamento de permissões no seu aplicativo.

Além disso, esta biblioteca também oferece uma solução de armazenamento de dados fácil de usar baseada no DataStore.

<h1>Instalação</h1>

Para utilizar esta biblioteca no seu projeto, basta adicionar as seguintes linhas no seu arquivo build.gradle do app:

````
implementation 'com.github.tumusx:easy-permissions:1.0.2
````
E adicione também no repositorio do settings.gradle a linha 
````
maven { url 'https://jitpack.io' }
````

<h1>Como usar</h1>
<h2>Solitando permissões</h2>
Para solicitar permissões, basta usar o método requestPermissions:

````
val easyPermissions = EasyPermissions(activity)

easyPermissions.requestPermissions(Manifest.permission.CAMERA)
````

:white_check_mark: Você também pode solicitar várias permissões ao mesmo tempo, por exemplo:
````
val easyPermissions = EasyPermissions(activity)
val permissions = arrayOf(
    Manifest.permission.CAMERA,
    Manifest.permission.WRITE_EXTERNAL_STORAGE
)

easyPermissions.requestPermissions(permissions)
````

:bangbang: Ambas as formas acima deverá ser chamada no onCreate.

Para executar as respostas de aceites da permissão, é necessário introduzir as seguintes linhas de código:
````
PermissionsListener.permissionListenerReceive(object : PermissionsListener {
            override fun onSuccessPermission() {
                Log.d("SUCCESS", "SUCCESS")
            }

            override fun onErrorPermission() {
                Log.d("ERROR", "ERROR")
            }

            override fun onRejectPermission() {
                Log.d("REJECTED", "REJECTED")
            }
        })
````

Para verificar se as permissões estão cedidas, deverá adicionar a seguinte linha:

````
val easyPermissions = EasyPermissions(activity)
val permissions = arrayOf(
    Manifest.permission.CAMERA,
    Manifest.permission.WRITE_EXTERNAL_STORAGE
)

if (easyPermissions.checkPermissions(permissions)) {
    // Todas as permissões foram concedidas
} else {
    // Alguma das permissões não foi concedida
}
````

:bangbang: Caso queira verificar somente uma permissão invés de uma lista: 
````
val permissions = Manifest.permission.CAMERA 
````

:white_check_mark: Também é possível escrever dados primitivos usando DataStore, veja os exemplos a seguir:

````
val easyPreferences = EasyPreferences(context)

lifecycleScope.launch {
    easyPreferences.insertValuesPreferences("FIRST_TEST", "FIRST TEST")
}
````

Para ler qualquer valor salvo: 
````
lifecycleScope.launch {
  val value = easyPreferences.readValuePreferences("FIRST_TEST", String::class)
    value.collect { testValue ->
        Log.d("FIRST_TEST", testValue as String)
    }
}
````
