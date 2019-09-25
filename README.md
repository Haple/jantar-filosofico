# Proposta do Jantar Filosófico
Esse projeto tem a finalidade de apresentar o problema do Jantar dos
Filósofos, proposto por Edsger Dijkstra para ilustrar problemas de
sincronização e maneiras de resolvê-lo.
O problema eme si é consiste em uma mesa redonda com 5 filósofos
que alternam entre pensar e comer. Na mesa estão dispostos cinco garfos
ao lado de cada filósofo. Para comer, um filósofo precisa de dois garfos
para conseguir comer (muitos acreditam que seria mais intuitivo se a 
comida fosse chinesa e os filósofos tivessem 5 hashis sobre a mesa ao
invés de garfos). Fica claro que dois filósofos não devem segurar o
mesmo garfo ao mesmo tempo. O desafio está em garantir que todos os 
filósofos alternem entre comer e pensar sem nunca terem que ficar
esperando infinitamente para pegar os dois garfos.


## Visão geral
O projeto foi feito usando a IDE Apache Netbeans, com a linguagem
Java 1.8. O projeto em si possui uma classe para representar o
Filósofo, uma para representar o garfo (onde foi usada a capacidade
de sincronismo da palavra chave do Java "synchronized") e uma classe
para representar a interface gráfica do jantar.

## Garfo
Sua função é oferecer as funções de "segurar" e "soltar", cada uma
com a palavra chave "synchronized" para assegurar que mesmo que dois
filósofos tentem manipular o garfo ao mesmo tempo, apenas um terá
sucesso e o outro entrará em modo de espera até que o outro filósofo
solte o garfo. O método "soltar", além de garantir que o filósofo que
está soltando é o mesmo que o dono, avisa o próximo filósofo para sair
do modo de espera.

## Filósofo
Cada filósofo recebe dois garfos (os quais possuem IDs). O primeiro
garfo de cada filósofo (aquele que ele vai tentar pegar primeiro)
será o garfo de menor ID. Isso evita a situação em que todos os
filósofos tentam pegar os garfos, por exemplo, da direita. Cada um
deles ficaria esperando o garfo que o próximo filósofo está segurando
e todos ficariam em modo de espera (starvation ou inanição). Se 
baseando no garfo de menor ID, o último filósofo usaria como primeiro
garfo o mesmo que o do primeiro filósofo, ou seja, o penúltimo filósofo
conseguiria pegar os dois garfos, assim quebrando com o ciclo e
impedindo um deadlock.
Ainda por cima, o filósofo possui os métodos de pensar e comer, cada um
com um tempo de espera.

## Classe principal
Essa classe cuida da inicialização dos garfos e dos filósofos e fica
acompanhando o seus estados para retransmitir para a interface. A mesma
oferece ao usuário a métrica de "injustiça" que seria a diferença entre
a maior e a menor quantidade de vezes que um filósofo comeu.
O usuário também pode modificar o tempo máximo que um filósofo come
e pensa para que possa tornar o andamento do programa mais lento ou mais
rápido.
