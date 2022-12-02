# Merge Tag VS Include Tag

## Include Tag
`<include></include>`

include 태그를 사용하면, 여러 파일로 나눌수 있다.

복잡하거나 아주 긴 GUI 를 구현하는데 도움이 됩니다.


## Merge Tag
`<merge></merge>`

merge 태그를 사용하면, include 와 같습니다.

다만, 부모 Layout 이 없어서, 중첩된 Layout 이 아니게 됩니다.

레이아웃을 그릴 때, depth 가 깊어 질수록 성능은 저하됩니다.

이를, merge 태그를 사용해 성능 개선을 할수 있습니다.