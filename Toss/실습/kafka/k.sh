#!/usr/bin/env bash
# Kafka 실습용 단축 명령
#   ./k.sh produce              체결 메시지 보내기 (입력 형식  계좌:내용   예: A:삼성전자 10주)
#   ./k.sh consume <그룹이름>    그룹에 소속된 컨슈머로 읽기 (파티션/오프셋/키 표시)
#   ./k.sh group <그룹이름>      그룹의 파티션 분배, 커밋 오프셋, LAG 보기
#   ./k.sh members <그룹이름>    그룹의 컨슈머별 담당 파티션 개수 보기
#   ./k.sh reset <그룹이름>      그룹 오프셋을 처음으로 되돌리기 (컨슈머 모두 끈 상태에서)
#   ./k.sh topic                토픽 파티션 정보 보기
BIN="/opt/kafka/bin"
BS="--bootstrap-server localhost:9092"
TOPIC="trade-executed"

case "$1" in
  produce)
    docker exec -it kafka $BIN/kafka-console-producer.sh $BS --topic $TOPIC \
      --property parse.key=true --property key.separator=: ;;
  consume)
    [ -z "$2" ] && { echo "그룹 이름을 주세요: ./k.sh consume ledger"; exit 1; }
    docker exec -it kafka $BIN/kafka-console-consumer.sh $BS --topic $TOPIC --group "$2" \
      --property print.key=true --property print.partition=true --property print.offset=true 2>/dev/null ;;
  group)
    docker exec kafka $BIN/kafka-consumer-groups.sh $BS --describe --group "$2" 2>/dev/null ;;
  members)
    docker exec kafka $BIN/kafka-consumer-groups.sh $BS --describe --group "$2" --members 2>/dev/null ;;
  reset)
    docker exec kafka $BIN/kafka-consumer-groups.sh $BS --group "$2" --topic $TOPIC \
      --reset-offsets --to-earliest --execute 2>/dev/null ;;
  topic)
    docker exec kafka $BIN/kafka-topics.sh $BS --describe --topic $TOPIC ;;
  *)
    sed -n '2,8p' "$0" ;;
esac
