package br.com.fatec.les;

import java.sql.SQLException;
import java.time.LocalDate;

import br.com.fatec.les.DAO.ClienteDao;
import br.com.fatec.les.model.Cidade;
import br.com.fatec.les.model.Cliente;
import br.com.fatec.les.model.Endereco;
import br.com.fatec.les.model.EntidadeDominio;
import br.com.fatec.les.model.Usuario;

public class Teste {

	public static void main(String[] args) throws SQLException {
		ClienteDao clienteDao = new ClienteDao();
		
		Cidade cidade = new Cidade();
		cidade.setId(8L);
		
		Usuario usuario = new Usuario();
		usuario.setEmail("qwnerqwe");
		usuario.setSenha("knwerkjwnerq");
		
		Endereco endereco = new Endereco();
		endereco.setBairro("nwerjqwer");
		endereco.setCep("8888");
		endereco.setCidade(cidade);
		endereco.setComplemento("qmwelkrqwer");
		endereco.setFavorito(false);
		endereco.setReferencia("kqwnerlqwer");
		endereco.setNumero(100);
		endereco.setLogradouro("kwmerlkqwer");

		Cliente cliente = new Cliente();
		cliente.setDataNascimento(LocalDate.now());
		cliente.setNome("qwmerlkqwer");
		cliente.setNumeroDocumento("lkwqelrkqwer");
		cliente.setNumeroTelefone("lwqenrwer");
		cliente.setUsuario(usuario);
		cliente.setEndereco(endereco);
		
		System.out.println(clienteDao.salvar((EntidadeDominio) cliente));

	}

}
