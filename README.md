# Gauss

Aplicação Java para resolver sistemas de equações lineares com qualquer quantidade de equações pelo método de Gauss-Seidel.

## Método de Gauss-Seidel

 1. Considere o seguinte sistema de equações:
```
3y + 2z = 28
4x + 2z = 24
2x + 3y = 16
```

2. Padronize as equações de modo que todas tenham as mesmas variáveis, na mesma ordem:
```
0x + 3y + 2z = 28
4x + 0y + 2z = 24
2x + 3y + 0z = 16
```
3. Extraia do sistema os coeficientes e, com eles, monte uma matriz:

|x    |y    |z    |Res|
|:---:|:---:|:---:|--:|
|**0**|3    |2    |28 |
|4    |**0**|2    |24 |
|2    |3    |**0**|16 |

4. Divida os coeficientes de cada linha *i* pelos de cada uma das demais linhas *j*, ignorando a última coluna. Caso, para alguma linha *i*, as divisões resultarem no mesmo valor, o sistema **não tem solução**.

5. Troque a ordem das linhas para que não existam zeros na diagonal principal:

|x    |y    |z    |Res|
|:---:|:---:|:---:|--:|
|**4**|0    |2    |24 |
|2    |**3**|0    |16 |
|0    |3    |**2**|28 |

6. Torne **1** o 1º elemento da diagonal principal, dividindo toda a 1ª linha pelo elemento a ser tornado 1 (4):

|x    |y    |z    |Res|
|:---:|:---:|:---:|--:|
|**1**|0    |1/2  |6  |
|2    |**3**|0    |16 |
|0    |3    |**2**|28 |

7. Torne **0** todos os demais elementos da coluna 1.
	- Na posição (2, 1) temos 2. Para torná-lo 0, tome a linha onde acabou de implantar 1, multiplique todos os seus elementos por -2 (que é o oposto do número que desejamos “zerar”) e some então os resultados aos valores da linha onde deseja implantar 0.

	- Na posição (3, 1) temos 0. Nada muda.


|x    |y    |z    |Res|
|:---:|:---:|:---:|--:|
|**1**|0    |1/2  |6  |
|0    |**3**|-1   |4  |
|0    |3    |**2**|28 |

8. Repita os passos 6 e 7 para cada elemento da diagonal principal. Ao finalizar, temos:

|x    |y    |z    |Res|
|:---:|:---:|:---:|--:|
|**1**|0    |0    |2  |
|0    |**1**|0    |4  |
|0    |0    |**1**|8  |

9. Os valores da coluna 4 são a solução do sistema de equações lineares:
	- x = 2
	- y = 4
	- z = 8
