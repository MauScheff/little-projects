module Main where

n = 9
max_weight = 64500
weight = 0:[15300, 5300, 19100, 6605, 23999, 13755, 14578, 24943, 4301]
value = 0:[232, 73, 201, 50, 141, 79, 48, 38, 133]
name = "":["BofA", "WFC", "Chase", "M&T", "BB&T", "First National", "PNC", "HSBC", "Bank of the West"]

main = do
     putStrLn $ "Max Rebate: " ++ show (fst(m!!n!!max_weight)) ++ "\nUsers: " ++ show (snd(m!!n!!max_weight))

-- Free top-down dynamic programming!
m = [[m' i w | w <- [0..max_weight]] | i <- [0..n+1]]

m' 0 w = (0, [])
m' i w | weight!!i > w = m!!(i - 1)!!w
m' i w | fst (m!!(i - 1)!!w) > fst (m!!(i - 1)!!(w - (weight!!i))) + value!!i = m!!(i - 1)!!w
m' i w | otherwise  = (fst (m!!(i - 1)!!(w - (weight!!i))) + value!!i, (i:snd(m!!(i - 1)!!(w - (weight!!i)))))
