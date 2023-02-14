// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

data class Usuario(val nome:String, var email:String, val id:Int, var nickname:String = nome){
    val formacoesInscritas = mutableListOf<Formacao>();
}

data class ConteudoEducacional(var nome: String, var duracaoHoras:Int? = null){
    override fun toString() = "\tNome: $nome\n\tDuração: ${duracaoHoras?: "Não definida"}";

}

data class Formacao(val nome: String) {
    val inscritos = mutableListOf<Usuario>();
    val conteudos = mutableListOf<ConteudoEducacional>();
    val requisitos = mutableListOf<ConteudoEducacional>();
	
    constructor(nome:String, vararg listaDeConteudos: ConteudoEducacional):this(nome){
        listaDeConteudos.forEach{
            this.conteudos.add(it);
        }
    }
    
    fun adicionarConteudos(vararg conteudos: ConteudoEducacional){
        conteudos.forEach{
            this.conteudos.add(it);
        }
    }
    
    fun adicionarPreRequisitos(vararg conteudos: ConteudoEducacional){
        conteudos.forEach{
            this.requisitos.add(it);
        }
    }
    
    fun matricular(vararg usuarios: Usuario) {
        usuarios.forEach{
            this.inscritos.add(it)
            it.formacoesInscritas.add(this);
        }
        
    }
    
    fun listarConteudos(){
        if(this.conteudos.size == 0) println("Não Há conteúdos registrados nessa formação.");
        else{
            println("Conteúdos:");
           	conteudos.forEach(){
                println("•$it\n");
            }
        }
    }
}

fun main() {
    //TODO("Analise as classes modeladas para este domínio de aplicação e pense em formas de evoluí-las.")
    //TODO("Simule alguns cenários de teste. Para isso, crie alguns objetos usando as classes em questão.")
    val user1 = Usuario("joao","jm@gmail.com", 1);
	val user2 = Usuario("maria", "mary@gmail.com", 2, nickname = "marimary");
    
    println(user1);
    println(user2);
    
    
    val formacao_kotlin = Formacao("Kotlin")
    
    formacao_kotlin.adicionarPreRequisitos(
    	ConteudoEducacional("Lógica de Programação"),
        ConteudoEducacional("Introdução à Programação")
    )
    
    formacao_kotlin.adicionarConteudos(
    	ConteudoEducacional("Introdução ao Kotlin"),
        ConteudoEducacional("Programação Orientada à objeto em Kotlin")
    )
    
    formacao_kotlin.listarConteudos();
    formacao_kotlin.matricular(user1, user2);
    
    println("Inscrições de ${user1.nome}: \n  ${user1.formacoesInscritas}");
    
}
