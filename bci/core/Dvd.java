package bci.core;

/**
 * Extende {@link Obra} com atributos específicos a Dvd
 * 
 * @see Obra
 */
public class Dvd extends Obra {
    private String _igac;
    private Criador _realizador;

    /**
     * Construtor que cria um Dvd com atributos preenchidos,
     * usa o construtor da Obra para as informações gerais para todas as Obras
     * 
     * @param id Id específico para o Dvd
     * @param title título do Dvd
     * @param price preço do Dvd
     * @param categoria Categoria do Dvd
     * @param copies número de cópias do Dvd
     * @param igac igac do Dvd
     * @param realizador Criador do Dvd
     */
    public Dvd (int id, String title, int price, Categoria categoria, int copies, String igac, Criador realizador){
        super(id, title, price, categoria, copies);
        _realizador = realizador;
        _igac = igac;

    }

    /**
     * Devolve o Criador do Dvd
     * @return instancia de Criador do Dvd
     */
    public String getCriadores(){
        return _realizador.getNome();
    }

    /**
     * {@inheritDoc}
     * 
     */
    public String toString(){
        return "DVD - " + super.getTitle() + " - " + super.getPrice() + " - " + super.getCategoria().toString() + " - " + _realizador.getNome() + " - " + _igac ;

    }

    public boolean searchSpecificObras(String termo){
        if(super.getTitle().toLowerCase().contains(termo.toLowerCase()) || _realizador.getNome().toLowerCase().contains(termo.toLowerCase())){
            return true;
        }
        return false;
    }
}
