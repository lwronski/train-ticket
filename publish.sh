#!/usr/bin/env bash
set -eu

array=(
    ts-rebook-service
    ts-train-service
    ts-admin-basic-info-service
    ts-config-service
    ts-inside-payment-service
    ts-route-plan-service
    ts-travel-plan-service
    ts-admin-order-service
    ts-consign-price-service
    ts-news-service
    ts-route-service
    ts-travel-service
    ts-admin-route-service
    ts-consign-service
    ts-notification-service
    ts-seat-service
    ts-travel2-service
    ts-admin-travel-service
    ts-contacts-service
    ts-order-other-service
    ts-security-service
    ts-admin-user-service
    ts-order-service
    ts-assurance-service
    ts-execute-service
    ts-payment-service
    ts-station-service
    ts-user-service
    ts-auth-service
    ts-preserve-other-service
    ts-ticket-office-service
    ts-verification-code-service
    ts-basic-service
    ts-food-map-service
    ts-preserve-service
    ts-ticketinfo-service
    ts-voucher-service
    ts-cancel-service
    ts-food-service
    ts-price-service
  )


for i in "${array[@]}"; do   # The quotes are necessary here
    echo "$i"
    cd "$i"
    docker build -t "$i:v5" .
    docker tag "$i:v5" "lwronski/$i:v5"
    docker push "lwronski/$i:v5"
    cd ..
done
