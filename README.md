# projeto do Curso de Jetpack Compose: Utilizando Lazy Layout e Estados

//aqui foi utilizada Lazy Column e Lazy Row para exibir o conteudo de uma Lista. Dessa forma, o desempenho do app não é prejudicado caso a lista seja grande demais.

//gerenciamento de Estado teve de ser aplicado em campos de TextField, para que a mudança do conteudo 'onValueChange' aconteça para o usuario do app

//foi aplicado um filter de uma List para exibir conteudos filtrados de acordo com os termos digitado pelo usuario do app

Ordem das Branchs do Projeto:
1. LazyLayout
2. Form and State - usando UiState e Stateful com Stateless Composables. Nessa técnica, o Stateful simula o funcionamento do ViewModel - mas sem conseguir garantir o Estado em recomposição por virar a tela
3. ViewModel - o poderoso conjunto de ViewModel com UiState para segurar o Estado da tela em recomposições, e que garante o padrão MVVM futuro
