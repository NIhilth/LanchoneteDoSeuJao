public class ValorInvalidoException extends RuntimeException{
    public ValorInvalidoException(String coisa){super(coisa + " com valor abaixo de 0!");}
}
