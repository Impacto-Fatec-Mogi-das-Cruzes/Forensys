package com.forensys.service.terminal;

import com.forensys.core.context.ApplicationContext;
import com.forensys.core.filestructure.FileMetadata;
import com.forensys.core.filestructure.concrete.TextFile;

public class OpenInitialTextFile {
    public static void execute() {
        TextFile initialTextFile = new TextFile();
        initialTextFile.setType("text");
        initialTextFile.setMetadata(new FileMetadata("report.txt", null, false, false, "a", 0, "never", "ever", true));
        initialTextFile.setContent("""
POLÍCIA CIVIL

DIVISÃO DE INVESTIGAÇÕES ESPECIAIS

PROCESSO Nº 2026-0714-A

INVESTIGADOR,

Você foi designado para analisar o notebook pessoal de Ares Valença, desenvolvedor de software encontrado morto em seu apartamento na noite de 14 de julho de 2026.

A causa oficial da morte foi registrada como suicídio por overdose. No entanto, inconsistências encontradas durante a investigação preliminar levantaram dúvidas sobre as circunstâncias do caso. Registros de acesso remoto, tentativas recentes de exclusão de arquivos e relatos de comportamento suspeito da vítima indicam que a hipótese de crime não pode ser descartada.

Antes de sua morte, Ares trabalhava na AlfaDyne Technologies, uma das maiores empresas de tecnologia do país. Colegas relataram que, nas semanas anteriores ao incidente, ele demonstrava preocupação constante com assuntos relacionados ao trabalho. Segundo testemunhas, a vítima afirmava ter descoberto informações que poderiam comprometer pessoas influentes dentro da empresa.

Durante a perícia, foi recuperado um notebook pertencente à vítima. Embora parte do conteúdo tenha sido apagada ou danificada, os especialistas conseguiram restaurar os arquivos e disponibilizar o sistema para análise.

Seu objetivo é examinar o conteúdo do dispositivo em busca de evidências que permitam responder às seguintes perguntas:

• O que Ares descobriu?
• Quem possuía interesse em silenciá-lo?
• A morte foi realmente um suicídio?
• Se houve crime, quem foi o responsável?
• Quais provas sustentam essa conclusão?

Você terá acesso ao sistema de arquivos da vítima, registros de atividades, projetos de desenvolvimento e ao histórico de mensagens do aplicativo corporativo AlfaTalk.

Utilize os recursos disponíveis para conduzir sua investigação. As evidências podem estar escondidas em locais inesperados, misturadas a arquivos comuns ou distribuídas entre diferentes documentos.

Quando acreditar ter encontrado provas suficientes, utilize o formulário de relatório para registrar suas conclusões.

Boa sorte, investigador.

A verdade está em algum lugar dentro deste notebook.
                """);
        ApplicationContext.getInstance().openFile(initialTextFile);
    }
}
