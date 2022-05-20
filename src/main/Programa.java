/**
 @author Andre Luis, Nicoli (21689), Talita (21261) e Maria Alice (21249).
 @since 2022.
 */

package main;

import bd.daos.Cinemas;
import bd.dbos.Cinema;

import java.util.ArrayList;

public class Programa
{
    public static void main (String[] args)
    {
        try
        {
                int cod = 0;
                String  codEdi;
                String nom, nomShop, salas, insta, num, complemento, cep = "";
                String procura;
                char decisao = ' ';
                int opcao = -1;
                boolean opcaoMenu = false;
                boolean sair = false;
                boolean erro = true;

                do
                {
                    while(erro)
                    {
                        erro = false;
                        opcaoMenu = false;
                        System.out.println("  ______  __  .__   __.  _______ .___  ___.      ___      \n" +
                                " /      ||  | |  \\ |  | |   ____||   \\/   |     /   \\     \n" +
                                "|  ,----'|  | |   \\|  | |  |__   |  \\  /  |    /  ^  \\    \n" +
                                "|  |     |  | |  . `  | |   __|  |  |\\/|  |   /  /_\\  \\   \n" +
                                "|  `----.|  | |  |\\   | |  |____ |  |  |  |  /  _____  \\  \n" +
                                " \\______||__| |__| \\__| |_______||__|  |__| /__/     \\__\\ \n" +
                                "                                                          ");

                        System.out.println("Bem-vindo(a) ao sistema CRUD de cinemas!\n");
                        System.out.println("| MENU");
                        System.out.println("[0] Sair do programa");
                        System.out.println("[1] Adicionar um novo cinema ao sistema");
                        System.out.println("[2] Acessar os cinemas adicionados");
                        System.out.println("[3] Editar informacao de algum cinema");
                        System.out.println("[4] Excluir algum cinema do sistema\n");

                        try
                        {
                            System.out.print("R: ");
                            opcao = Teclado.getUmInt();

                            if (opcao == 0)
                                System.exit(0);
                        }
                        catch (Exception error) { System.err.println("\nDigite corretamente uma das opcoes!"); erro = true; }

                        try
                        {
                            if (opcao > 4 || opcao < 0)
                            {
                                System.err.println("\nDigite corretamente uma das opcoes!\n\n");     // arrumar para fazer um looping
                                    erro = true;
                            }
                        }
                        catch (Exception error) {
                            System.err.println(error.getMessage());
                            erro = true;
                        }
                    }
                    while (!opcaoMenu)
                    {
                        // INCLUIR CINEMA
                        switch (opcao)
                        {
                            case 1:
                                System.out.println("\n-----------------------------------------------------");
                                System.out.println("Criacao de um novo cinema...");
                                System.out.println();

                                try
                                {
                                    System.out.print("Codigo.: ");
                                    cod = Teclado.getUmInt();
                                }
                                catch (Exception error)
                                {
                                    System.err.println("Digite o codigo corretamente!");
                                    break;
                                }

                                System.out.print("Nome do cinema.: ");
                                nom = Teclado.getUmString();

                                System.out.print("Nome do shopping.: ");
                                nomShop = Teclado.getUmString();

                                System.out.print("Quantidade de salas.: ");
                                salas = Teclado.getUmString();

                                try
                                {
                                    System.out.print("Digite o CEP do cinema.: ");
                                    cep = Teclado.getUmString();

                                    if (cep.length() != 8)
                                        throw new Exception();

                                    for (int x = 0; x < cep.length(); x++)
                                    {
                                        if (!Character.isDigit(cep.charAt(x)))
                                            throw new Exception();
                                    }
                                }
                                catch (Exception error)
                                {
                                    System.err.println("\nDigite o CEP corretamente!");
                                    break;
                                }

                                System.out.print("Digite o Instagram do cinema.: ");
                                insta = Teclado.getUmString();

                                System.out.print("Digite o numero do cinema.: ");
                                num = Teclado.getUmString();

                                System.out.print("Digite o complemento do cinema.: ");
                                complemento = Teclado.getUmString();

                                try
                                {
                                    Cinemas.incluir(new Cinema(cod, nom, nomShop, salas, cep, insta, num, complemento));
                                    System.out.println("\n- CINEMA" + " " + (nom) + " " + "INCLUIDO");

                                    System.out.print("\nPressione ENTER para continuar... ");
                                    Teclado.getUmString();
                                }
                                catch (Exception error)
                                {
                                    System.err.println ("\n" + error.getMessage());
                                    System.out.println("\n-----------------------------------------------------\n");
                                }
                                opcaoMenu = true;
                                erro = true;
                            break;

                            // ACESSAR UM CINEMA
                            case 2:
                                System.out.println("\n-----------------------------------------------------");
                                System.out.println("Acessar o banco de dados dos cinemas...");
                                System.out.println("Digite [0] para acessar UM cinema");
                                System.out.println("Digite [1] para acessar TODOS cinema");
                                System.out.print("R: ");
                                decisao = Teclado.getUmChar();

                                if(decisao == '1')
                                {
                                    ArrayList<Cinema> ac = Cinemas.getCin();

                                    for(int p = 0; p < ac.size(); p++)
                                    {
                                        System.out.println("\n--------------------------------------------");
                                        System.out.println(ac.get(p));
                                        Logradouro logradouro = (Logradouro)ClienteWS.getObjeto(Logradouro.class, "https://api.postmon.com.br/v1/cep", ac.get(p).getCep());
                                        System.out.print("\n" + logradouro + "\n");
                                    }
                                }
                                else if (decisao == '0')
                                {
                                    System.out.print("\nDigite um CEP.: ");
                                    procura = Teclado.getUmString();

                                    for (int x = 0; x < cep.length(); x++)
                                    {
                                        if (!Character.isDigit(cep.charAt(x)))
                                            throw new Exception();
                                    }

                                    try
                                    {
                                        System.out.println(Cinemas.getCinema(procura));
                                        Logradouro logradouro = (Logradouro) ClienteWS.getObjeto(Logradouro.class, "https://api.postmon.com.br/v1/cep", Cinemas.getCinema(procura).getCep());
                                        System.out.print("\n" + logradouro + "\n");
                                    }
                                    catch (Exception error)
                                    {
                                        System.err.println("O CEP nao existe ou nao foi digitado corretamente!");
                                        break;
                                    }
                                }
                                else
                                {
                                    System.err.println("Digite corretamente!\n");
                                }

                                System.out.print("\nPressione ENTER para continuar... ");
                                Teclado.getUmString();

                                opcaoMenu = true;
                                erro = true;
                            break;

                            // EDITAR UM CINEMA
                            case 3:
                                System.out.println("\n-----------------------------------------------------");
                                System.out.println("Editar um cinema no banco de dados...");

                                System.out.print("Digite o CEP de um cinema.: ");
                                codEdi = Teclado.getUmString();

                                System.out.println("\n"+Cinemas.getCinema(codEdi));

                                System.out.print("\nPressione ENTER para continuar... ");
                                Teclado.getUmString();

                                try
                                {
                                    System.out.print("Codigo.: ");
                                    cod = Teclado.getUmInt();
                                }
                                catch (Exception error)
                                {
                                    System.err.println("Digite o codigo corretamente!");
                                    break;
                                }

                                System.out.print("Novo nome do cinema.: ");
                                nom = Teclado.getUmString();

                                System.out.print("Novo nome do shopping.: ");
                                nomShop = Teclado.getUmString();

                                System.out.print("Nova quantidade de salas.: ");
                                salas = Teclado.getUmString();

                                try
                                {
                                    System.out.print("Digite o novo CEP do cinema.: ");
                                    cep = Teclado.getUmString();

                                    if (cep.length() != 8)
                                        throw new Exception();

                                    for (int x = 0; x < cep.length(); x++)
                                    {
                                        if (!Character.isDigit(cep.charAt(x)))
                                            throw new Exception();
                                    }
                                }
                                catch (Exception error)
                                {
                                    System.err.println("\nDigite o CEP corretamente!");
                                    break;
                                }

                                System.out.print("Digite o novo Instagram do cinema.: ");
                                insta = Teclado.getUmString();

                                System.out.print("Digite o novo numero do cinema.: ");
                                num = Teclado.getUmString();

                                System.out.print("Digite o novo complemento do cinema.: ");
                                complemento = Teclado.getUmString();

                                try
                                {
                                    Cinemas.alterar(new Cinema(cod, nom, nomShop, salas, cep, insta, num, complemento));
                                    System.out.println("\n- CINEMA" + " " + (nom) + " " + "EDITADO");
                                }
                                catch (Exception error)
                                {
                                    System.err.println ("\n" + error.getMessage());
                                    System.out.println("\n-----------------------------------------------------\n");
                                }

                                System.out.print("\nPressione ENTER para continuar... ");
                                Teclado.getUmString();

                                opcaoMenu = true;
                                erro = true;

                            break;

                            // EXCLUIR UM CINEMA
                            case 4:
                                System.out.println("\n-----------------------------------------------------");
                                System.out.println("Excluir algum cinema do banco de dados...");

                                System.out.print("Digite o CEP do cinema que gostaria de excluir.: ");
                                codEdi = Teclado.getUmString();

                                try {
                                    for (int x = 0; x < codEdi.length(); x++) {
                                        if (!Character.isDigit(codEdi.charAt(x)))
                                            throw new Exception();
                                    }
                                }
                                catch (Exception error)
                                {
                                    System.err.println("\nDigite o CEP corretamente!");
                                    break;
                                }

                                try
                                {
                                    Cinemas.getCinema(codEdi);
                                    Cinemas.excluir(Cinemas.getCinema(codEdi).getCodigo());

                                    System.out.println("\n- CINEMA EXCLUIDO");

                                    System.out.print("\nPressione ENTER para continuar... ");
                                    Teclado.getUmString();
                                }
                                catch (Exception error)
                                {
                                    System.err.println(error.getMessage());
                                    System.out.println("\n-----------------------------------------------------\n");
                                }
                                opcaoMenu = true;
                                erro = true;
                            break;
                        }
                    }
                }
                while(!sair);
        }
        catch (Exception erro)
        {
			erro.printStackTrace();
            System.out.println (erro.getMessage());
        }
    }
}
