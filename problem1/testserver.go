package main

import (
	"encoding/json"
	"flag"
	"log"
	"math/rand"
	"net/http"
	"time"
)

func main() {
	listenAddr := flag.String("http.addr", ":8090", "http listen address")
	flag.Parse()

	http.HandleFunc("/primes", handler([]int{2, 3, 5, 7, 11, 13}))
	http.HandleFunc("/fibo", handler([]int{1, 1, 2, 3, 5, 8, 13, 21}))
	http.HandleFunc("/odd", handler([]int{1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23}))
	http.HandleFunc("/rand", handler([]int{5, 17, 3, 19, 76, 24, 1, 5, 10, 34, 8, 27, 7}))

	log.Fatal(http.ListenAndServe(*listenAddr, nil))
}

func handler(numbers []int) func(http.ResponseWriter, *http.Request) {
	return func(w http.ResponseWriter, r *http.Request) {
		waitPeriod := rand.Intn(550)
		log.Printf("%s: waiting %dms.", r.URL.Path, waitPeriod)

		time.Sleep(time.Duration(waitPeriod) * time.Millisecond)

		x := rand.Intn(100)
		if x < 10 {
			http.Error(w, "service unavailable", http.StatusServiceUnavailable)
			return
		}

		w.Header().Set("Content-Type", "application/json")
		w.WriteHeader(http.StatusOK)

		json.NewEncoder(w).Encode(map[string]interface{}{"numbers": numbers})
	}
}
